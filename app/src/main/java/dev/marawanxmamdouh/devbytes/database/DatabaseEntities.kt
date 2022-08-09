package dev.marawanxmamdouh.devbytes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.marawanxmamdouh.devbytes.domain.Video

@Entity
data class DatabaseVideo(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

/**
 * Map [DatabaseVideo] to [Video]
 */
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}