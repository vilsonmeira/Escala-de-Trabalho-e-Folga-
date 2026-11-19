package br.com.hjc.escalatrabalho

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private var callback: ConnectivityManager.NetworkCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 72, 48, 48)
        }
        val title = TextView(this).apply {
            text = "HJC Ponto"
            textSize = 28f
        }
        status = TextView(this).apply {
            text = "Monitoramento ainda não iniciado."
            textSize = 17f
            setPadding(0, 32, 0, 32)
        }
        val start = Button(this).apply {
            text = "Verificar Wi-Fi agora"
            setOnClickListener { checkWifi() }
        }
        layout.addView(title)
        layout.addView(status)
        layout.addView(start)
        setContentView(layout)
    }

    private fun checkWifi() {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network: Network? = manager.activeNetwork
        val capabilities = network?.let { manager.getNetworkCapabilities(it) }
        val onWifi = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        status.text = if (onWifi) {
            "Celular conectado a uma rede Wi-Fi.\nPróxima etapa: validar a rede HJC-PONTO e vincular este aparelho ao funcionário."
        } else {
            "Nenhuma conexão Wi-Fi ativa identificada."
        }
    }
}
