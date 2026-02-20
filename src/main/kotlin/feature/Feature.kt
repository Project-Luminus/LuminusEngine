package one.nxeu.feature

import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.InstanceManager
import one.nxeu.server.AbstractServer

fun AbstractServer.asFeatureContext(): Feature.Context {
    return Feature.Context(
        handler = this.globalEventHandler,
        instanceManager = this.instanceManager,
        defaultInstance = this.defaultInstance,
    )
}

interface Feature {
    class Context(
        val handler: GlobalEventHandler,
        val instanceManager: InstanceManager,
        val defaultInstance: InstanceContainer,
    ) {}

    val name: String

    fun hook(server: AbstractServer) {
        val context = server.asFeatureContext()
        items(context)
        commands(context)
        events(context)
    }

    fun items(context: Context) {}
    fun commands(context: Context) {}
    fun events(context: Context) {}
}