# How to contribute to NumaHOP.

## Did you find a bug ?

> Do not open an issue if the bug is a security vulnerability in our
> dependencies. We have automatic dependencies report provided by github in the
> security tab. But if the vulnerability is in the NumaHOP code itself please
> do

Before submitting a bug report ensure the bug was not already reported by
searching the [issues](https://github.com/biblibre/NumaHOP-code/issues). If you
find an issue that describe the same bug, you can add a comment describing your
situation and add more context information.

If your unable to find an open issue addressing the problem, [open a new
one](https://github.com/biblibre/NumaHOP-code/issues/new). Be sure to include a
clear **title** with as much relevant information as possible. For example
`Creation of duplicate document units while importing csv notices.` is good
descriptive title while `Import didn't work as expected` is too vague. The more
information you provide the greater the likelihood of the bug being fixed is.

The bug report form should ask you the following information:
- A description of what you wanted to do and/or expected to happen:
- A description of what happened with potential error messages (from the
front-end) or screen shots.
- Steps to reproduce: A list of steps that allows you to see the bug in action.
- Browser used: Useful to know which browser was used to access NumaHOP.

### Issue life cycle. 

Just after the bug report was created the issue has the label `Triage` which
means that it was not reviewed by a developer and needs to be evaluated. Once
the issue was reviewed if the bug report is too vague it will be labeled as
`Needs more info`. If we can't reproduce the bug in any shape or form it will
be marked as `Won't fix`. If the bug is easy to fix and relatively straight
forward it will receive the `Good first issue`. These are good issues to tackle
for new contributors. If a contributor managed to reproduce your bug the report
will receive the `Reproduced` label and your bug is eligible to a patch. Then a
contributor might develop a patch resolving your bug and make a pull request
for it mentioning the bug report issue number. Finally when the issue is merged
it will receive the `Fixed` label and the issue will be closed.

## Are you writing a patch ?

- Fork the repository and create a new branch with a descriptive name for the
patch you plan on submitting.
- Develop your patch following the coding guidelines.
- Open a new pull request with the patch.
- Ensure the PR description clearly describes your solution to the problem you
fixed. Include the issue number if applicable.
- Before submitting for review please ensure the content of the PR conforms to
the coding guidelines and make sure the `make checks` command succeeds when
applicable.

### Commits & Commit messages

> General rule: separate formatting and adapting to coding guidelines from the
contribution itself. 

If the part of the code you are modifying needs re-formatting, do the
formatting in a separate commit. If the part of the code you are modifying does
not follow the coding guidelines, try to adapt the code to follow those in a
separate commit (not always possible). Then, add the commit with the fix
itself. This facilitate the reviewing of the merge request a lot.

In the commit message where the fix is situated please include a test plan for
the fix. This makes the review process a lot quicker and easier for the
reviewer.

# Coding guidelines.

## Coding guideline violations.

If you find coding guidelines violations in the source code you can submit an
issue with the tag: `Coding Guidelines violation`. These issues are not urgent
and will not be included in the milestones. However these are often labeled as
`Good first issue` and are great to get familiar with the codebase.

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

### (JAVA 1) Prefer `Optional` over  `null`.

`null` is error prone and unclear. When seeing the signature of a function it
is not immediately clear if the result can be `null` for this reason prefer the
use of `Optional` when possible. If not possible please annotate the argument
or parameter with the `@Nullable` annotation.

### (JAVA 2) Code Comments

Each java method in a class should be documented using javadoc comments `\**
*\` at the exception of the API handlers these should be commented using the
swagger annotations.

## HTML/CSS/JS
### (Front-End 1) Code Comments

In JavaScript files that defines any new angular module, use this kind of
comment

```js
/**
 * @memberOf NumaHOP
 * @class NumaHOP.MyModule
 */
```

For each AngularJs service, controller, filter or directive, use the following
comment replacing service by any of the previously mentioned item:

```js
/**
 * @memberOf NumaHOP.MyModule
 * @ngdoc service
 * @name MyService
 * @ngInject //Document the injected angular js services.
 * @description This is my angularjs service.
 */
 function myAngularObject($http) {

 }
 angular.module('NumaHOP.MyModule').service('myService', myService);
```

Similarly for a controller use:
```js
/**
 * @memberOf NumaHOP.MyModule
 * @ngdoc controller 
 * @name myController
 * @ngInject //Document the injected angular js services.
 * @description This is my angularjs controller.
 */
 function myController($http) {

 }
 angular.module('NumaHOP.MyModule').service('myService', myService);
```

To document a function or a property:
```js
/**
 * @property a
 */
var obj = {};

/**
 * @memberOf NumaHOP.MyModule.My{Controller/Service/Directive}
 * @func myFunction
 * @description This is my angularjs service.
 * @param a
 */
function myFunction(a) {
    ...
}
```

## Api

### (API 1) Specification.

The API of NumaHOP must respect the [Open-Api v3.1 specification](https://spec.openapis.org/oas/v3.1.1.html).

### (API 2) Use of verbose annotation.

On handlers prefer the use of the methods mapping (eg: `@GetMapping`, `@PostMappin`, etc) instead of the more verbose `@RequestMapping`.

### (API 3) Route request Handlers.

There can only be 1 handler per methods on each routes.
For example this is disallowed:
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

Instead use a route parameter:
```java
@RequestMapping("/api/rest/user")
class UserController {
    
    @GetMapping
    fn getCurrentUser() {
        // ...
    }

    @GetMapping("/{id}")
    fn getUser(@PathVariable String id) {
        // ...
    }
}
```

Minimize the number of different routes for one controller. If a route has the
4 methods following defined it should have its own class:
- POST (creation)
- GET (fetching)
- DELETE (suppression)
- PUT (update) If not it can be located in its parent class.

Disallowed:
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
Instead a `UserProfileController` class should be created.

Allowed:
```java
@RequestMapping("/api/rest/user")
class UserController {

    /* ... /api/rest/user handlers */

    @GetMapping("/search")
    fn searchUsers() { /* ... */ }
}
```

### (API 4) Front-end API usage.

Similarly in the front-end each back-end controller must have a matching
`$ressource` call. With the definition of the route, method, and query
parameters.

Due to how AngularJs auto generates the handlers, these are the route expected
in the API: 

| method | url             | meaning   |
| ------ | --------------- | --------- |
| POST   | `/<object>`     | create    |
| GET    | `/<object>`     | list      |
| GET    | `/<object>/:id` | get by id |
| DELETE | `/<object>/:id` | delete    |
| POST   | `/<object>/:id` | update    |

Any other routes should be manualy declared in the `$ressource` call. A few examples:

| method | url                    | meaning                      |
| ------ | ---------------------- | ---------------------------- |
| GET    | `/<object>/search?...` | a search with filters        |
| POST   | `/<object>/task`       | perform a task on the object |
