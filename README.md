# EthScan

An app for exploring the transactions of an Ethereum address.


**Components and libraries used:**
- [x] Navigation Architecture Component
- [x] ConstraintLayout
- [x] RxJava
- [x] Retrofit
- [x] JVM Unit tests
- [x] Espresso tests
- [x] SwipeRefreshLayout

![screenshot](screenshot.png)

things to refactor...
- [ ] make a separate file for 'e2e' tests, using a real endpoint
- [ ] model decoupled from Activity lifecycle
- [ ] use Retrofit Rx plugin
    * add to readme
- [ ] don't re-initialise Retrofit regularly
- [ ] MVP pattern
   * [ ] injected presenter
- [ ] more JVM tests
- [ ] do not be wasteful when setting up recyclerview
- [ ] repository pattern for data source
- [ ] use multi modules for information hiding with `internal`
- [x] dependency injection
   * [x] data source
- [x] use DI in Tests - mock the datasource
- [x] memory leak from RxJava
    * [x] UI test: rotate the phone while a request is in progress
    * [x] fix app crash if rotated while loading

### current feature branch
