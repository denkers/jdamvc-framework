# JDA MVC Framework

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

JDA MVC is a desktop application [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) framework  
The framework is designed for data-driven, gui intensive applications and simplifies common MVC tasks such as controller and database model management,
routing and view display. See [StudentCore](https://github.com/kyleruss/student-core) for a project example of the jdamvc-framework

## Features
- Controllers
- Controller GET/request & POST/submit methods
- Inter-controller & controller->View data passing 
- Routing
- Route Grouping
- Models
- QueryBuilder statement chaining to create complex queries
- DQL Statements: 
  * SELECT + Column Alias
  * WHERE + Conjunction/Disjunction
  * JOIN (inner, left, right) as well as join conditionals
  * ORDER BY & GROUP BY
  * OFFSET
  * COUNT
  * FIRST
- DML Statements
  * INSERT
  * UPDATE
  * DELETE
- Transactions
- Sessions
- Authentication
- Views
- Comprehensive logging

## Usage
#### Controllers
Each user made controller extends `jdamvc.engine.controller.Controller`  
A controller method must return a `jdamvc.engine.view.View`  

```
public class UserController extends Controller
{
  public View getLogin()
  {
    //Return a LoginView 
    return prepareView(new LoginView());
  }
}
```

Controller methods can pass data back to the view using the `jdamvc.engine.controller.Message` class  
Simply package your data in a `JSONArray` and send it to the view

```
Message userInfo  = new Message(userData); //Wrap the userData JSONArray in a Message
return prepareView(new UserView(userInfo)); //Return the view with the userInfo Message data attached
```

Controller methods can accept URL parameters  
These URL parameters and their names are defined in the route pattern

```
//Controller method has classID URL parameter
public View getClassPage(Integer classID) 
{
  //Retrieve the class info for the class with ID = classID
  return prepareView(new ClassView(new Message(classDetails)));
}
```

Controllers can read POST data by accessing the `controllerData` field
```
String name = (String) controllerData.getMessage('user_id');
```

Controllers can redirect to another route using the `jdamvc.engine.core.RouteHandler`
```
RouteHandler.go(String routeName, Message data);
```

#### Routing
Routes in the application call controller methods when a particular route path pattern is matched  
Users can define routes in the `Routes.xml` file  
Related routes can be grouped up into a `RouteGroup` where the children of the group will extends the parents path

```
<?xml version="1.0" encoding="UTF-8"?>

<Routes>
    <!-- Call the controller method getHomeView() on MasterController -->
    <Route name="getHome" controller="MasterController" method="getHomeView" path="/home" />
    
    <RouteGroup prefix="user">
        <Route name="getProfile" controller="UserController" method="getUserProfile" path="/profile" />
    </RouteGroup>
</Routes>
```

#### Models
In jdamvc you can model database entities as Objects  
To create a model you must create a class that extends `jdamvc.engine.model.Model`  
You must also override the `initTable()` method where you will set your table parameters

```
public class User extends Model
{
  @Override
  public void initTable()
  {
    table       = "users"; //Table name
    primaryKey  = "user_id"; //Column name of the primary key
  }
}
```

#### Querying (DQL)
jdamvc allows uers to create complex queries with the `jdamvc.engine.core.database.QueryBuilder` class  
You can get a `QueryBuilder` instance from a model using the `builder()` method and then begin constructing your query  
Each query must call the `get()` method at the end of their statement chain where each query will return a `JsonArray`  
The following example demonstrates how to create a query with a **WHERE** clause and **column alias**

```
JsonArray classDetails  =   new ClassesModel().builder().where("id", "=", "" + classId)
                            .select("id", "Class ID")
                            .select("name", "Class name")
                            .select("teacher_id", "Teacher ID")
                            .select("created_date", "Date created")
                            .get();
```


**JOINS**
jdamvc supports left, right and inner JOIN statements
The following example demonstrates an inner-join with the `QueryBuilder`  
You can add extra `WHERE` clauses on the join condition using the `filter` method 
```
JsonArray results   =   user.builder().join(new Join("users", "class_enrolments", "username", "user_id", Join.JoinType.INNERR_JOIN)
                        .filter(new Conditional("username", "=", username)))
                        .join("class_enrolments", "classes", "class_id", "id", Join.JoinType.INNERR_JOIN)
                        .select("classes.id", "Class ID")
                        .select("classes.name", "Class name")
                        .select("classes.description", "Class description")
                        .select("class_enrolments.semester_num", "Semester")   
                        .get();
```


**Other DQL Statements**
The following statements are also supported by jdamvc
  * WHERE  Conjunction/Disjunction
  * ORDER BY & GROUP BY
  * OFFSET
  * COUNT
  * FIRST

#### Manipulating Data (DML)
jdamvc supports all common DML statements such as `INSERT`, `UPDATE`, `DELETE`  
Many of the DML operations can be done directly on model instances for convenience

**INSERT**
First create an instance of the model and set the column values for the new row  
When you are ready to save the row into the table call the `save`

```
User user = new User()
user.set("username", postData.getMessage("registerUsername"));
user.set("password", passHash);
user.set("firstname", postData.getMessage("registerFirstname"));
user.set("lastname", postData.getMessage("registerLastname"));

boolean saved = user.save();
```

**UPDATE**
First get a model instance, set the updated parameters  
then call the `update` method on the model

```
User user =   new User(userid);
user.set('name', 'Joe");
boolean updated = user.update();
```

**DELETE**
You can delete an existing record by first getting the model instance then calling `delete()`

```
User user = new User(userid);
boolean deleted = user.delete();
```

#### Transactions
jdamvc supports transactions which ensure that all statements in a block  
are executed successfully otherwise they are rolled back  
The following example demonstrates transactions where if the User record is not successfully or 
partially inserted then the statement is not committed

```
try(DataConnector conn  = new DataConnector())
{
  conn.setQueryMutator(); //DML mode
  user.setActiveConnection(conn); //Let user model instance use the conn connection
  user.save(); //insert the row
  
  ResultSet results = conn.getResults();
  
  //Record failed to be saved 
  if(!results.next()) throw new SQLException();
  else
  {
    //Record was successfully saved!
    conn.commitTransaction();
  }
}

catch(SQLException e)
{
  conn.rollbackTransaction();
  conn.closeConnection();
}
```

#### Views
Views are the graphical display and interaction for the end-user  
Views are typically served up by controllers in jdamvc in response to a request  
You can create a view by extending `jdamvc.engine.view.GUIView`  
The class also comes with a number of overridable methods that can be used to assist with component initialization

```
public class HomeView extends GUIView implements ActionListener
{
  public HomeView(ControllerMessage data)
  {
    super(data);
  }
  
  //Initialize at the minimum the panel 
  @Override
  protected void initComponents()
  {
    panel = new JPanel();
    ...
  }
  
  //Initialize the images and extra files needed for this view
  @Override 
  protected void initResources()
  {
    try
    {  
      backgroundImage = ImageIO.read(new File("background.jpg"));
    }
    
    catch(IOException e) 
    {
      ...
    }
  }
  
  //Initialize all the listeners for the components in the view
  @Override
  protected void initListeners()
  {
    homeButton.addActionListener(this);
    ...
  }
}
```

## Getting started

### Prerequisites
- JDK 1.8+
- Maven 3.3+


### Installation
- [Download]https://github.com/kyleruss/jdamvc-framework/releases/latest) the latest release  
OR `git clone` the repository and run `mvn package`
- Reference the `jdamvc.jar` library in your project 
- **Optional:** Copy the template `application` into your project sources, rename the packages where necessary


## License
jdamvc-framework is available under the MIT License  
See [LICENSE](LICENSE) for more details
