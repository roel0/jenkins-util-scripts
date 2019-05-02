/**
 * Create a resource lock based on multiple resources
 *
 * <pre>{@code
 *
 *      super_lock(['lock1', 'lock2', 'lock3']) {
 *          sh 'dosomething'
 *      }
 *
 * }</pre>
 *
 * @param lock_names: List of the resource locks that need
 *                    to be acquired
 * @param body: Sequence of statements that will be executed
 *              When the locks are acquired
 */
def call(lock_names, body)
{
    def prev = body

    for (lock_name in lock_names.reverse()) {
        prev = _lock(lock_name, prev)
    }

    prev()
}

def _lock(lock_name, body)
{
    return {
        lock(quantity: 1, resource: lock_name) {
            body()
        }
    }
}
