package swing

import org.intellij.lang.annotations.JdkConstants
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import scope.FlickrService
import scope.Result
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.ScrollPane
import javax.swing.*
import kotlin.concurrent.thread


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://www.flickr.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val flick = retrofit.create(FlickrService::class.java)

fun main() {
    val frame = JFrame()
//    frame.preferredSize = Dimension(200, 100)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.layout = BorderLayout(5, 5)

    val topPanel = JPanel()
    topPanel.layout = FlowLayout()

    topPanel.add(JLabel("Add"))
    val text = JTextField(20)
    topPanel.add(text)

    val list = JList<String>()
    list.layoutOrientation = JList.VERTICAL
    val bottom = ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED)
    bottom.add(list)
    frame.add(bottom, BorderLayout.SOUTH)

    val search = JButton("Search")
    search.addActionListener {
        flick.search(text.text, 12).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                println("OnResponse")
                response.body()?.let {
                    val model = DefaultListModel<String>()
                    for (s in it.photos.photo) {
                        model.addElement(s.title)
                    }
                    SwingUtilities.invokeLater {
                        list.model = model
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                println("OnFailure")
            }
        })
//        thread {
//            println(text.text)
//            println("Starting")
//            Thread.sleep(2_000)
//            println("Ending")
//            SwingUtilities.invokeLater {
//                search.text = text.text
//                text.text = ""
//                search.isVisible = false
//                text.isEnabled = false
//            }
//        }
    }
    topPanel.add(search)

    frame.contentPane.add(topPanel)
    frame.pack()
    frame.isVisible = true
}
