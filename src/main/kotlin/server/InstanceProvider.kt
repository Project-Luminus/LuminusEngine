package one.nxeu.server

import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.InstanceManager

interface InstanceProvider {
    val instanceManager: InstanceManager
    val defaultInstance: InstanceContainer
}