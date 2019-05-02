/**
 * Get the current duration of the running job
 *
 * @return Integer holding the current duration in seconds
 */
def call()
{
    return ((System.currentTimeMillis() - currentBuild.startTimeInMillis)/1000)
}
