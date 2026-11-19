package br.com.hjc.escalatrabalho

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private lateinit var detail: TextView

    companion object {
        private const val LOCATION_PERMISSION_REQUEST = 410
        private const val TARGET_WIFI = "HJC-PONTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 64, 48, 48)
        }

        val title = TextView(this).apply {
            text = "HJC Ponto"
            textSize = 28f
        }
        val subtitle = TextView(this).apply {
            text = "Hortifruti Jardim Celeste"
            textSize = 16f
            setPadding(0, 8, 0, 28)
        }
        status = TextView(this).apply {
            text = "Pronto para verificar a rede da empresa."
            textSize = 18f
            setPadding(0, 16, 0, 16)
        }
        detail = TextView(this).apply {
            text = "Rede esperada: $TARGET_WIFI"
            textSize = 14f
            setPadding(0, 8, 0, 24)
        }
        val check = Button(this).apply {
            text = "Verificar conexão HJC-PONTO"
            setOnClickListener { verifyCompanyWifi() }
        }
        val permission = Button(this).apply {
            text = "Autorizar identificação do Wi-Fi"
            setOnClickListener { requestWifiPermission() }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)
        layout.addView(detail)
        layout.addView(check)
        layout.addView(permission)
        setContentView(layout)
    }

    private fun requestWifiPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
        } else {
            status.text = "Permissão já concedida. Toque em verificar conexão."
        }
    }

    private fun verifyCompanyWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            status.text = "Precisamos da permissão de localização para identificar o nome da rede Wi-Fi."
            requestWifiPermission()
            return
        }

        val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivity.activeNetwork
        val capabilities = network?.let { connectivity.getNetworkCapabilities(it) }
        val onWifi = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true

        if (!onWifi) {
            status.text = "Seu celular não está conectado a uma rede Wi-Fi."
            detail.text = "Conecte-se à rede HJC-PONTO e tente novamente."
            return
        }

        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        @Suppress("DEPRECATION")
        val ssid = wifi.connectionInfo?.ssid?.removeSurrounding("\"") ?: "<desconhecida>"

        when {
            ssid == TARGET_WIFI -> {
                status.text = "Rede HJC-PONTO identificada."
                detail.text = "Conexão confirmada neste teste. O registro de ponto e a vinculação do funcionário ainda serão integrados ao servidor."
            }
            ssid == "<unknown ssid>" || ssid == "0x" || ssid == "<desconhecida>" -> {
                status.text = "O Android não informou o nome da rede."
                detail.text = "Confira se a localização do aparelho está ativada e se a permissão foi concedida. Depois tente novamente."
            }
            else -> {
                status.text = "Wi-Fi conectado, mas não é a rede da empresa."
                detail.text = "Rede detectada: $ssid. Rede esperada: $TARGET_WIFI."
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            status.text = if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                "Permissão concedida. Agora toque em verificar conexão."
            } else {
                "Permissão não concedida. Sem ela, o Android pode ocultar o nome da rede Wi-Fi."
            }
        }
    }
}
