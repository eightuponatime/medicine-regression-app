# noname project for regression analysis
<br/>
## installation 

### requirements
jdk 21 or higher
gradle 8.x (it will be auto-installed via wrapper, just go forward)

### setup
```sh
git clone https://github.com/eightuponatime/medicine-regression-app.git
cd medicine-regression-app

./gradlew build
./gradlew run
```

### running tests

1. run all tests.
```sh 
./gradlew test
```
2. run certain test
   - you can use prepared .sh script
   ```sh
   ./check.sh
   ```
   - or specify name by yourself
   ```sh
   ./gradlew test --tests IQRDetectorTest --stacktrace
   ```

<br/>
## commit rules
- `feat`: new feature
- `fix`: bug fix
- `test`: adding or updating tests
- `refactor`: code refactoring
- `docs`: documentation changes
- `build`: build systems or dependencies
- `chore`: maintenance tasks (routine that doesn't change any functionality)
