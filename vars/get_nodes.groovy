/**
 * Get all node objects
 *
 * @return List containing all node objects
 */
def call()
{
    return jenkins.model.Jenkins.instance.nodes + jenkins.model.Jenkins.instance
}
