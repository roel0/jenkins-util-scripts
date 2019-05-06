import hudson.tasks.test.AbstractTestResultAction

class Test
{
    def name
    def log
}

class TestResults
{
    def failed
    def passed
    def skipped
}

def extract_test_results(def test_results)
{
    def target_list = []

    test_results.inject([]) { result, test_action ->
        def test = new Test()

        test.name = test_action.getName()
        test.log = test_action.getStdout()

        target_list << test
    }

    return target_list
}

/**
 * Fetch details about the parsed junit files
 *
 * @return A TestResults object
 */
def call()
{
    def result_action = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    def result = new TestResults()

    if (result_action) {
        result.failed = extract_test_results(result_action.getFailedTests())
        result.skipped = extract_test_results(result_action.getSkippedTests())
        result.passed = extract_test_results(result_action.getPassedTests())
    }

    return result
}
