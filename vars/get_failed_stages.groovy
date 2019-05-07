import com.roel0.jenkinsutils.PipelineUtils


/**
 * Get a list with the names of the stages which
 * have failed. This only applies for stages which
 * were initiated by the super_stage API
 */
def call()
{
    return PipelineUtils.get_failed_stages()
}
