package com.example.gymkana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Challenge[] challenges;
    private static ArrayList<Challenge> completedChallenges;
    private static Challenge currentChallenge;
    private int currentChallengeIndex;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        else{
            preferences = getApplicationContext().getSharedPreferences("SAVED_DATA", Context.MODE_PRIVATE);
            currentChallengeIndex = preferences.getInt("CURRENT_CHALLENGE", 0);
            if(currentChallengeIndex == 6){
                Intent finalChallenge = new Intent(this, FinalChallengeActivity.class);
                startActivity(finalChallenge);
            }
            else {
                setContentView(R.layout.activity_main);

                final NavController navController = Navigation.findNavController(findViewById(R.id.fragment_host));
                BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.uncompleted_tasks_page:
                                        navController.navigate(R.id.currentChallenge);
                                        return true;
                                    case R.id.completed_challenges_page:
                                        navController.navigate(R.id.completedChallenge);
                                        return true;
                                }
                                return false;
                            }
                        };
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
                getSupportActionBar().setIcon(R.drawable.ic_pokemon);
                loadChallenges();
            }
        }
    }

    public static Challenge getCurrentChallenge() {
        return currentChallenge;
    }

    public static ArrayList<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Snackbar.make(findViewById(R.id.button),"¡Habéis desbloqueado la siguiente prueba!", Snackbar.LENGTH_SHORT).setAction("Ignorar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            }).show();
            completedChallenges.add(currentChallenge);
            if(currentChallengeIndex < 5){
                this.currentChallengeIndex++;
                preferences.edit().putInt("CURRENT_CHALLENGE", currentChallengeIndex).apply();
                currentChallenge = challenges[currentChallengeIndex];
            }
            else if(currentChallengeIndex == 5){
                this.currentChallengeIndex++;
                preferences.edit().putInt("CURRENT_CHALLENGE", currentChallengeIndex).apply();
                Intent finalChallenge = new Intent(this, FinalChallengeActivity.class);
                startActivity(finalChallenge);
            }
            else{
                Intent finalChallenge = new Intent(this, FinalChallengeActivity.class);
                startActivity(finalChallenge);
            }
        }
    }

    private void loadChallenges(){
        this.challenges = new Challenge[6];
        completedChallenges = new ArrayList<>();

        String emoji = new String(Character.toChars(0x1F917));
        String emojiFire = new String(Character.toChars(0x1F525));
        //Prueba 0
        Challenge c0 = new Challenge("¡Bienvenidos a Gymkana! " + emoji +
                "\nEsto es una prueba. Las pruebas os indican qué debéis hacer o a dónde debéis ir para obtener el código que las resuelve." +
                "\nUna vez tengáis el código, pulsad el botón 'Resolver' para introducir el código y completar la prueba." +
                "\nPodréis revisar las pruebas que completéis en el menú 'Pruebas Completadas', en el botón derecho de la barra inferior." +
                "\nUna vez completéis todas las pruebas encontraréis a vuestros amigos secuestrados. ¿Estáis preparados?" +
                "\nIntroduce el código 1234 para cargar la primera prueba." +
                "\n¡Qué empiece el juego! " +emojiFire+emojiFire+emojiFire, "Un nuevo comienzo", R.drawable.ic_pokeballs, "1234");
        //Prueba 1
        Challenge c1 = new Challenge("Poseéis algo muy rico, no querréis que se descongele y se estropee, ¿verdad?", "¡Comienza la aventura!", R.drawable.ic_pikachu, "1910");
        //Prueba 2
        Challenge c2 = new Challenge("El código está escondido en la habitación donde aparece la palabra que " +
                "falta en la siguiente frase de Khal Drogo y Daenerys Targaryen:\n\n\t My sun and ____, moon of my life.\n\nPero tened cuidado, un monstruo " +
                "viene a veros...", "¡Cuidado con el monstruo!", R.drawable.ic_haunter, "0206");
        //Prueba 3
        emoji = new String(Character.toChars(0x1F480));
        Challenge c3 = new Challenge("Muy bien, habéis superado al monstruo. El lugar donde se encuentra " +
                "el siguiente gimnasio pokémon es parte del nombre de un superhéroe de Marvel muy cachondo, ¿sabéis cuál es?\n" +
                "\t(Pista: su nombre empieza por " + emoji+ " )", "¡Busca tu camino!", R.drawable.ic_squirtle, "1141");
        //Prueba 4
        Challenge c4 = new Challenge("¿A qué pokémon corresponde el nº 123 en la Pokédex Nacional?\n" +
                "Cuando lo adivinéis deberéis acudir al lugar donde lo podríais localizar.", "¿Cuál es este Pokémon?", R.drawable.ic_pokemon_quest, "1002");
        //Prueba 5
        Challenge c5 = new Challenge("Enhorabuena, habéis superado todos los gimnasios pokémon. " +
                "Pero para poder rescatar a vuestros amigos tendréis que encontrar la " +
                "llave que abre el candado.\n¿Os gustó la habitación de las estrellas? Volved allí " +
                "y encontrad todas las llaves que os puedan servir para liberar a vuestros amigos.", "¡Hora de buscar!", R.drawable.ic_scyther, "2014");
        challenges[0] = c0;
        challenges[1] = c1;
        challenges[2] = c2;
        challenges[3] = c3;
        challenges[4] = c4;
        challenges[5] = c5;
        currentChallenge = challenges[currentChallengeIndex];
        completedChallenges.addAll(Arrays.asList(challenges).subList(0, currentChallengeIndex));
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("Current_Task", currentChallengeIndex);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        currentChallengeIndex = savedInstanceState.getInt("Current_Task");
        if(preferences == null)
            preferences = getApplicationContext().getSharedPreferences("SAVED_DATA", Context.MODE_PRIVATE);
        loadChallenges();
    }

    @Override
    public void onBackPressed() {
        onStop();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}