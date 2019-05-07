# jenkins-util-scripts  ![jj](http://i65.tinypic.com/33ol9mo.jpg)
A collection of scripts, custom steps that can be used in a Jenkins pipeline

Some of the API's provided:

* Get a list of the failed stages
* Extract details about test results (junit)
* Add/Remove environment variabels to a node
* Delete jobs based on a condition
* Fetch plotting data from the current job/pipeline
* Run a script on multiple slaves
* ...

## Installation

Add this repository as a [shared library](https://jenkins.io/doc/book/pipeline/shared-libraries/) in your jenkins instance

## Usage

See the [example](https://github.com/roel0/jenkins-util-scripts/blob/master/example/Jenkinsfile) for more information.

### get_failed_stages

It is possible to get a list of the failed stages. However this is only possible when the stages use the super_stage wrapper instead of directly using the stage API by jenkins.

```groovy
try {
    super_stage("stage1") {
        // Do stuff
    }
    super_stage("stage2") {
        error "oei"
    }
} catch(e) {
}

println "Failed stages: ${get_failed_stages().join(',')}"
```
This will print out => Failed stages: stage2

### super_lock

Jenkins default locking API only allows the locking of one resource at the time. This becomes especially an issue if the number of locks is dynamicly programmed. Super_lock is a wrapper arround that API that allows the locking of multiple resources at the same time.

```groovy
 super_lock(['lock1', 'lock2']) {
     // Do stuff
 }
```

### get_junit_results

Jenkins supports the parsing of junit fail to determine the build result. However the results are not directly available to be used in a various number of reporting plugins. For example if you want to share the test results through e-mail, slack, ...

```groovy
 junit "test.xml"

 def results = get_junit_results()

 println "FAILED TC:"
 println "=========="

 for (def failed: results.failed) {
     println "Name ${failed.name}"
     println "Log: ${failed.log}"
     println "-----"
 } 
 ```
 
 ### Other
 See the [example](https://github.com/roel0/jenkins-util-scripts/blob/master/example/Jenkinsfile) for more examples ...
