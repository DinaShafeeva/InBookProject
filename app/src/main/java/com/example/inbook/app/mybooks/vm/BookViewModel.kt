package com.example.inbook.app.mybooks.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.mybooks.services.BookService
import com.example.inbook.domain.response.BookResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookViewModel( val interactor: BookInteractor,
                     val service: BookService): ViewModel() {

    private val bookLiveData: MutableLiveData<BookResponse> = MutableLiveData()
    private var bookMutableLiveData: MutableLiveData<Book> = MutableLiveData()

    private fun getBookMutableLiveDataByName(name:String): MutableLiveData<Book> {
        var data: Disposable = interactor.getBookByName(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data -> bookLiveData.value = data
                Log.d("Book: " , data.toString())
                Log.i("Book: ", data.items.get(0).volumeInfo.description)

                val book: Book = Book(bookLiveData.value?.items?.get(0)?.id ?:"-1",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.title ?: "name",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.authors?.get(0) ?: "author",
                    false,
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.description ?: "description",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.imageLinks?.smallThumbnail ?: "image")
                Log.d("book = ", book.nameOfBook)
                 bookMutableLiveData = MutableLiveData(book)

            },
                {
                        error -> Log.e("Error" , error.toString())
                })

        return bookMutableLiveData
    }
    fun getBook(name: String): LiveData<Book> = getBookMutableLiveDataByName(name)

    fun isBookWasRead(book: LiveData<Book>): Boolean = interactor.isBookWasRead(book.value)

    fun addBook(book: LiveData<Book>) = interactor.addBook(book.value)

    fun like(book: LiveData<Book>) = interactor.like(book.value)
}