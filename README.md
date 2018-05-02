# JDA MVC Framework

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

JDA MVC is a desktop application [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) framework  
The framework is designed for data-driven, gui intensive applications and simplifies common MVC tasks such as controller and database model management,
routing and view display. See [StudentCore](https://github.com/kyleruss/student-core) for a project example of the jdamvc-framework

## Features
- Controllers
- Controller GET/request & POST/submit methods
- Inter-controller & Controller->View data passing 
- 

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
