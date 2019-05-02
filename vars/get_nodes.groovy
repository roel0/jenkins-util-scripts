/**
 * Get all node objects
 *
 * @return List containing all node objects
 */
def call(body)
{
    return jenkins.model.Jenkins.instance.nodes
}
