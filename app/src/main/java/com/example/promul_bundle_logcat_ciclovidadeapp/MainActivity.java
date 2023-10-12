package com.example.promul_bundle_logcat_ciclovidadeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textoSalvable = (EditText) findViewById(R.id.textoSalvable);
        /* Ejemplo de Bundle implícito de onCreate
        * - Cuando rotamos la posición del dispositivo de vertical a horizontal y viceversa, savedInstanceState guarda
        * el bundle con los valores del último activity. Algunos de estos valores por defect son
        *  viewHierarchyState, lifecycle, lastAutofillId, etc. Pero además de éstos, también guarda los valores
        * que se están usando como variables en el programa, como puede ser el contenido de la variable textoSalvable
        * que estoy usando en mi programa.
        * */
        if(savedInstanceState != null){
            for(String key: savedInstanceState.keySet()){
                Log.d("Contenido bundle", "Key -> " + key + "; Value -> " + savedInstanceState.get(key).toString());
            }

            /*Aquí está el contenido del bundle en vertical*/
            //Contenido bundle Key -> android:state; Value -> 2
            //Contenido bundle Key -> android:viewHierarchyState; Value -> Bundle[{android:views={16908290=android.view.AbsSavedState$1@2cb530d, 2131230774=android.view.View$BaseSavedState@6b457d3, 2131230785=android.view.View$BaseSavedState@dbddf10, 2131231175=TextView.SavedState{a2f0a09 start=0 end=0 text=vista vertical y tambien horizontal}}, android:focusedViewId=2131231175}]
            //Contenido bundle Key -> @android:autofillResetNeeded; Value -> true
            //Contenido bundle Key -> androidx.lifecycle.BundlableSavedStateRegistry.key; Value -> Bundle[{androidx.lifecycle.internal.SavedStateHandlesProvider=Bundle[{}]}]
            //Contenido bundle Key -> android:lastAutofillId; Value -> 1073741826
            //Contenido bundle Key -> android:fragments; Value -> android.app.FragmentManagerState@70c340e

            /*y como el bundle android:viewHierarchyState conserva el mismo valor al cambiar a horizontal*/
            //Contenido bundle Key -> android:state; Value -> 2
            //Contenido bundle Key -> android:viewHierarchyState; Value -> Bundle[{android:views={16908290=android.view.AbsSavedState$1@2cb530d, 2131230774=android.view.View$BaseSavedState@7711bd0, 2131230785=android.view.View$BaseSavedState@48267c9, 2131231175=TextView.SavedState{59b9ece start=0 end=0 text=vista vertical y tambien horizontal}}, android:focusedViewId=2131231175}]
            //Contenido bundle Key -> @android:autofillResetNeeded; Value -> true
            //Contenido bundle Key -> androidx.lifecycle.BundlableSavedStateRegistry.key; Value -> Bundle[{androidx.lifecycle.internal.SavedStateHandlesProvider=Bundle[{}]}]
            //Contenido bundle Key -> android:lastAutofillId; Value -> 1073741826
            //Contenido bundle Key -> android:fragments; Value -> android.app.FragmentManagerState@ef991ef
        }



    }
}