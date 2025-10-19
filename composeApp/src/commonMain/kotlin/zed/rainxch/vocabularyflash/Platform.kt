package zed.rainxch.vocabularyflash

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform