/**
 * Delete jobs base on a given condition
 *
 * <pre>{@code
 *     def condition = { it ->
 *         return it.getFullName().contains("dummy_job")
 *     }
 *     delete_jobs(condition)
 * }</pre>
 *
 * @param condition: Closure that accepts a job object as argument
 *                   and returns true if the given job must be deleted
 */
def call(condition)
{
    def instance = jenkins.model.Jenkins.instance

    instance.getAllItems(Job.class).each { item ->
        if (condition(item)) {
            println "Deleting ${item.getFullName()}"
            item.delete()
        }
    }
}
