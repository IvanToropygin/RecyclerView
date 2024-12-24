package otus.gpb.recyclerview


import android.icu.text.SimpleDateFormat
import java.util.Calendar
import kotlin.random.Random

object DataGenerator {

    private fun generateRandomName(): String {
        val names = listOf(
            "Alice",
            "Bob",
            "Charlie",
            "Diana",
            "Ethan",
            "Fiona",
            "George",
            "Hannah",
            "Ian",
            "Julia"
        )
        return names[Random.nextInt(names.size)]
    }

    private fun generateRandomMessage(): String {
        val messages = listOf(
            "Hello!",
            "How are you?",
            "What's up?",
            "See you later!",
            "Good morning!",
            "Good night!",
            "Have a nice day!",
            "Catch you later!",
            "Let's meet!",
            "Take care!"
        )
        return messages[Random.nextInt(messages.size)]
    }

    private fun generateRandomDate(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        val calendar = Calendar.getInstance()
        calendar.add(
            Calendar.MINUTE,
            -Random.nextInt(30)
        )
        return dateFormat.format(calendar.time)
    }

    fun generateChatItems(count: Int): MutableList<ChatItem> {
        return MutableList(count) { index ->
            ChatItem(
                id = index + 1,
                name = "[${index+1}] ${generateRandomName()}",
                date = generateRandomDate(),
                message = "[${index+1}] ${generateRandomMessage()}"
            )
        }
    }
}