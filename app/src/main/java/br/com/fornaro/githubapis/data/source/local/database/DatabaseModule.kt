package br.com.fornaro.githubapis.data.source.local.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "database"
        ).build()

    @Provides
    fun provideEmojiDao(db: AppDatabase) = db.emojiDao()

    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}