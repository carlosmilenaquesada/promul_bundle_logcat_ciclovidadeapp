package com.example.promul_bundle_logcat_ciclovidadeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /*
     * Qué es un Bundle:
     * - Un bundle es un paquete de valores que se envían entre un actívity y otro. Este
     * paquete de valores contiene valores de variables que son necesarias en el programa.
     *
     * -La intención del bundle es conseguir que cuando un activity desaparezca, los valores de las variables
     * que contiene no se pierdan con él. Por ejemplo, cuando cambiamos la vista de nuestra app
     * de vertical a horizontal, se pierden los valores de variables ya que se genera un nuevo activity,
     * así que con el bundle podemos rescatarlas, también cuando abandonamos o finalizamos un activity y para mostrar otro, etc.
     *
     * - Cuando iniciamos un activity, el primer método que se ejecuta es el onCreate, que de manera
     * implícitia ya incluye un bundle (llamado savedInstaceState) que contendrá los datos del activity
     * pretérito o será null si no existía un activity pretérito.
     *
     * - No solo existe el Bundle implícito de onCreate, si no que podemos crear Bundles según la necesidad
     * del programa para transferir valores entre activitys.
     */
    int contador = 0;

    /*En este ejemplo de Bundle, vamos a ver como se comportan dos tipos de view, uno es un
     * TextView cuyo contenido se pierde al cambiar de Activity, y el otro es un EditText cuyo
     * contenido se conserva y restaura automáticamente a través de savedInstanceState al cambiar de Activity*/
    TextView textoTextViewVolatil = null;
    EditText textoEditTextNoVolatil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoTextViewVolatil = (TextView) findViewById(R.id.textoTextViewVolatil);
        textoEditTextNoVolatil = (EditText) findViewById(R.id.textoEditTextNoVolatil);

        Button botonModificar = (Button) findViewById(R.id.botonModificar);
        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Vamos a tratar que ambos textos tanto el TextView como el EditText se actualicen,
                 * para posteriormente ver el comportamiento al voltear la pantalla*/
                textoTextViewVolatil.setText("" + contador);
                textoEditTextNoVolatil.setText("" + contador);
                contador++;
            }
        });
        /* Ejemplo de Bundle implícito de onCreate
         * - Cuando rotamos la posición del dispositivo de vertical a horizontal y viceversa, savedInstanceState guarda
         * el bundle con los valores del último activity. Algunos de estos valores por defect son
         *  viewHierarchyState, lifecycle, lastAutofillId, etc.
         *
         * Además, guarda algunos valores que se están mostrando por pantalla, como por ejemplo los EditText
         * */
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
                Log.d("Contenido bundle", "Key -> " + key + "; Value -> " + savedInstanceState.get(key).toString());
                textoTextViewVolatil.setText(savedInstanceState.get("textoTextViewVolatil").toString());
                contador = (int) savedInstanceState.get("contador");
                /*El contenido de textoTextViewVolatil se pierde, ya que es un textView. Otros tipos de view como pueden ser un EditText
                 * no pierden su contenido y se asigna automáticamente. Por ejemplo, si tenemos un EditText  que en el momento antes
                 * de voltear la pantalla tiene "7", al voltearla volverá a mostrar "7"*/

                /*Al voltear, perdemos el contenido de textoTextFieldVolatil, ya que es un TextView, sin embargo, el contenido
                 * de textoEditTextNoVolatil no se pierde (se almacena a través del bundle savedInstaceState en  2131231217=TextView.SavedState{e76dd3c start=0 end=0 text=10} y
                 * se restaura automáticamente) ya que es un EditText */

                /*
                 * Entonces, hemos perdido tanto contador como el contenido del EditText, pero se puede recuperar de la
                 * sobreescribiendo el método onSaveInstanceState, siguiente manera:
                 * */
            }
        }
    }

    /*El método onSaveInstanceState se ejecuta justo antes de eliminar el activity activo y crea un bundle (outState) que guarda los estados del actíty antes de eliminarse,
    * y pasa como parámetro ese bundle (que será el savedInstanceState) en el método onCreate que se ejecuta en la creación del siguiente activity.
    * Este bundle guarda información de la aplicación y algunos valores como los EditText, que como hemos visto antes, se
    * restaurarán automáticamente.
    * Esta sería la funcionalidad básica del método onSaveInstanceState, pero nosotros queremos que
    * además nos guarde algunas variables y valores que no se guardarán de forma automática, así que
    * tenemos que añadir al bundle outState dichos valores, sobreescribiendo el método de la siguiente manera:
    * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //Ahora, generamos el bundle (outState) que guardará los valores por defecto
        super.onSaveInstanceState(outState);

        //Y también le añadimos (usando putTipodato y añadiendo un key + valor para poder restaurarlo posteriormente)
        // los que queremos guardar, que en este ejemplo serán tanto la variable
        //contador como la del TextView (la del EditText no, ya que como hemos visto, se guarda y
        // restaura de forma implícita)
        outState.putInt("contador", contador);
        outState.putString("textoTextViewVolatil", String.valueOf(textoTextViewVolatil.getText()));

        /*ahora solamente nos queda restaurar los valores. eso se hace en el onCreate del siguiente
         * activity y rescatando del bundle los valores guardados. El código de restauración se ejecuta en el onCreate y es el siguiente
         * (no lo voy a incluir en este programa para no entorpecer la explicación anterior, pero el funcionamiento es el siguiente):
         *
         *
         * if(savedInstanceState != null){//la primera vez es null, solo tiene valor a partir del segundo onCreate, que es cuando ha habido un cambio de activity
         *   //los valores por defecto o el texto EditText no es necesario restaurarlos, ya que se hace automáticamente en el onCreate a través de  super.onCreate(savedInstanceState); de forma implícita.
         *
         *   textoTextViewVolatil.setText(savedInstanceState.get("textoTextViewVolatil").toString());
         *   contador = (int) savedInstanceState.get("contador");
         * }
         * */

    }
}