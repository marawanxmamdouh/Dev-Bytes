package dev.marawanxmamdouh.devbytes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.marawanxmamdouh.devbytes.database.VideosDatabase
import dev.marawanxmamdouh.devbytes.database.asDomainModel
import dev.marawanxmamdouh.devbytes.domain.Video
import dev.marawanxmamdouh.devbytes.network.Network
import dev.marawanxmamdouh.devbytes.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}