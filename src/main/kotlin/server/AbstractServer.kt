package one.nxeu.server

import io.github.oshai.kotlinlogging.KotlinLogging
import net.minestom.server.Auth
import net.minestom.server.MinecraftServer
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.InstanceManager
import one.nxeu.feature.Feature

abstract class AbstractServer : InstanceProvider {
    private val logger = KotlinLogging.logger {}
    private val registeredFeatures: MutableList<Feature> = mutableListOf()

    val mc: MinecraftServer = MinecraftServer.init(Auth.Online())
    val globalEventHandler: GlobalEventHandler = MinecraftServer.getGlobalEventHandler()
    override val instanceManager: InstanceManager = MinecraftServer.getInstanceManager()
    override val defaultInstance: InstanceContainer = instanceManager.createInstanceContainer()

    fun registerFeature(feature: Feature) {
        if (registeredFeatures.any({ it.name == feature.name })) {
            logger.info { "Feature with name ${feature.name} is already registered. Skipping." }
        } else {
            logger.info { "Registering feature ${feature.name}." }
            registeredFeatures.add(feature)
            feature.hook(this)
        }
    }

    fun registerFeatures(features: List<Feature>) {
        features.forEach { registerFeature(it) }
    }

    fun run(address: String = "0.0.0.0", port: Int = 25565) {
        logger.info { "Starting server on $address:$port" }
        mc.start(address, port)
    }
}