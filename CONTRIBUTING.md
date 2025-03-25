# How to contribute to NumaHOP.

## Did you find a bug ?

- Do not open an issue if the bug is a security vulnerability in our dependencies.
- Ensure the bug was not already reported by searching the [issues](https://github.com/biblibre/NumaHOP-code/issues).
- If your unable to find an open issue addressing the problem, [open a new one](https://github.com/biblibre/NumaHOP-code/issues/new).
Be sure to include a **title and clear description** with as much relevant information as possible.

## Are you writing a patch ?

- Fork the repository and create a new branch with a desciptive name for the patch you plan on submitting.
- Develop your patch folowing the coding guidelines.
- Open a new pull request with the patch.
- Ensure the PR description clearly descibes the solution. Include the issue number if applicable.
- Before submitting for review please ensure the content of the PR comforms to the coding guidelines and make sure the `make checks` command succeeds.

### Commits 

General rule: if you submit code that fixes previous code that violates those
guidelines on top of your fix. Do a separate commit for the fix and the coding guidelines corrections.
Similarly speparate the formating of the code and the fixes in separate
commits. This facilitate the reviewing of the merge request.

# Coding guidelines.

## Coding guideline violations.

If you find coding guidelines violations in the source code you can submit an issue with the tag:
`Coding Guidelines violation`. These issues are not urgent and will not be
included in the milestones. However These are often good first issues to get
familiar with the codebase.

## File organization

Here is the global organization of the project:

``` 
src
├─ main
│  ├─ docker # All the docker related files.
│  ├─ java/fr/progilone/pgcn/
│  │  ├─ config # Java configuration classes
│  │  ├─ domain # Data Classes
│  │  ├─ repository # Storage abstractions 
│  │  ├─ service # buissiness
│  │  └─ web # Api handlers
│  ├─ webapp
│  │  ├─ assets # Static assets.
│  │  ├─ i18n # Translations.
│  │  └─ scripts
│  │     ├─ config # Configuration  definitions.
│  │     ├─ api # $ressources definitions.
│  │     ├─ services # Services.
│  │     ├─ components # Reusable components.
│  │     └─ app # Page Controllers and templates.
│  ├─ ressources # Ressources to be bundled in the jar. 
│  └─ scss # Styles
└── test
    ├─ javascript # front-end tests
    ├─ java # back-end tests
    └─ ressources # test ressources
```

## Java

### Prefer `Optional` over  `null`.

`null` is error prone and unclear. When seeing the signature of a function it is not immediatly clear if the result can be `null` 
for this reason prefer the use of `Optional` when possible. 
If not possible please annotate the argument or parameter with the `@Nullable` anotation.

### Code Comments

Each java method in a class should be documented using javadoc comments `\** *\` at the exeption of the api handlers these sould be commented using the swagger annotations.

## HTML/CSS/JS
### Code Comments

In javascript files that defines any new angular module, use this kind of comment
```js
/**
 * @class NumaHOP.MyModule
 * @memberOf NumaHOP
 */
```

For each angularjs service, use the following comment:
```js
/**
 * @function myService
 * @memberOf NumaHOP.MyModule
 * @description This is an angularjs service.
 */
```
If I have quite long codes in a controller or directive of MyModule and want to have a separate html file to document it, I will annotate the controller or directive as a class using full path. e.g.
```js
/**
 * @class angular_module.MyModule.MyController
 */
```

Then, we can annotate codes within the controller as member functions of MyController.
```js
/**
 * @name $scope.aScopeFunction
 * @function
 * @memberOf angular_module.MyModule.MyController
 * @description
 */
```

## Api

### Specification.

The api of NumaHOP must respect the [Open-Api v3.1 specification](https://spec.openapis.org/oas/v3.1.1.html).

### Route request Handlers.

On handlers prefer the use of the methods mapping (eg: `@GetMapping`) instead of the more verbose `@RequestMapping`.

There can only be 1 handler per methods on each routes.
For example this is disalowed:
```java
@RequestMapping("/api/rest/user")
class UserController {
    
    @GetMapping
    fn getCurrentUser() {
        // ...
    }

    @GetMapping(params = {"id"})
    fn getUser(@QueryParam String id) {
        // ...
    }
}
```

Minimize the number of different routes for one controller.
If a route has the 3 methods folowing defined it should have its own class:
- POST (creation)
- GET (fetching)
- DELETE (suppression)

If not it can be located in its parent class.

Disalowed:
```java
@RequestMapping("/api/rest/user")
class UserController {
    
    /* ... /api/rest/user handlers  */

    @GetMapping("/profile")
    fn getUserProfile() { /* ... */ }

    @PostMapping("/profile")
    fn createUserProfile() { /* ... */ }

    @DeleteMapping("/profile")
    fn deleteUserProfile() { /* ... */ }

    @PutMapping("/profile")
    fn updateUserProfile() { /* ... */ }
}
```
Intead a `UserProfileController` class shoud be created.

Allowed:
```java
@RequestMapping("/api/rest/user")
class UserController {
    /* ... /api/rest/user handlers */

    @GetMapping("/search")
    fn searchUsers() { /* ... */ }
}
```

Similarly in the front-end each back-end controller must have a matching `$ressource` call. 
With the definition of the route, method, and query parameters.

