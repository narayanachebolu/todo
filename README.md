# ToDo Demo Application

## Features
- @version based optimistic locking: Zero lock blocking, safe detection of collisions.
- Custom global @ControllerAdvice to handle OptimisticLockingFailureException → return HTTP 409 Conflict.
- If the client tries to update stale data ➜ Fails cleanly. Meaning, shows how two threads cause an OptimisticLockException.

## What is achieved:
- Full working **@version** example.
- A test that forces version conflicts
- How Optimistic locking protects from lost updates.

## Run it using curl
curl -X POST "http://localhost:8080/todos?title=Initial"
curl -X PATCH "http://localhost:8080/todos/1?title=Updated"

## How to run
- Unit tests: mvn test
- Integration tests: mvn verify -P integration-tests
