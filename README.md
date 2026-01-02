# noname project for regression analysis

## installation 

### requirements
- jdk 21 or higher
- gradle 8.x (auto-installed via wrapper)

### setup
```sh
git clone https://github.com/eightuponatime/medicine-regression-app.git
cd medicine-regression-app
./gradlew build
./gradlew run
```

### running Tests

1. run all tests:
```sh 
./gradlew test
```

2. run certain test:
   - use prepared script:
```sh
   ./check.sh
```
   - or specify name:
```sh
   ./gradlew test --tests IQRDetectorTest --stacktrace
```

## commit rules

- **feat**: new feature
- **fix**: bug fix
- **test**: adding or updating tests
- **refactor**: code refactoring
- **docs**: documentation changes
- **build**: build systems or dependencies
- **chore**: maintenance tasks (routine that doesn't change functionality)
