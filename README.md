# GithubAPIs

This project uses some GitHub APIs to show some data into the UI.

To fetch the emojis: https://api.github.com/emojis

To fetch users: https://api.github.com/users/:username

To fetch users repos: https://api.github.com/users/:username/:repos

## Architecture

To develop this app, this architecture has been built:

- 100% Kotlin;
- MVVM;
- Hilt for DI;
- Navigation Component;
- Flow for reactive programming;
- ViewBinding for binding;
- Retrofit for network;
- Room for DB;
- MockK for tests;

## CI/CD
Each new pull request will execute a pipeline that will test the app.

Each new merge to develop will execute a pipeline that will test, build apk and upload to [Github Release page](https://github.com/DouglasCF/GithubAPIs/releases).

## Tasks

The tasks has been planned used Notion where you can find them here: https://www.notion.so/fornaro/Github-APIs-App-9afb1a92dc394dd9b2597b59a23cf376
