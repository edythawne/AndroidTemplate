package edy.app.change.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.novoda.merlin.Bindable
import com.novoda.merlin.Connectable
import com.novoda.merlin.Disconnectable
import com.novoda.merlin.Merlin

abstract class ConnectionActivity : AppCompatActivity() {

    // Merlin Variable
    protected var merlin: Merlin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        merlin = createMerlin()
    }

    protected abstract fun createMerlin(): Merlin?

    protected fun registerConnectable(connectable: Connectable?) {
        merlin!!.registerConnectable(connectable)
    }

    protected fun registerDisconnectable(disconnectable: Disconnectable?) {
        merlin!!.registerDisconnectable(disconnectable)
    }

    protected fun registerBindable(bindable: Bindable?) {
        merlin!!.registerBindable(bindable)
    }

    override fun onStart() {
        super.onStart()
        merlin!!.bind()
    }

    override fun onPause() {
        merlin!!.bind()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        merlin!!.unbind()
    }

}