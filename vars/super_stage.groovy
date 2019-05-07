import com.roel0.jenkinsutils.PipelineUtils

/**
 * Wrapper around the stage API
 * which registers the stage if it has failed
 */
def call(name, body)
{
    try {
        stage(name) {
            body()
        }
    } catch (e) {
        PipelineUtils.register_failed_stage(name)
        throw e
    }
}
