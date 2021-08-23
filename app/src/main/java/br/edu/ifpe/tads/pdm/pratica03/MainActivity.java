package br.edu.ifpe.tads.pdm.pratica03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        public static final City [] cities = {
                new City("Recife", "Capital de Pernambuco"),
                new City("João Pessoa", "Capital da Paraíba"),
                new City("Natal", "Capital do Rio Grande do Norte"),
                new City("Fortaleza", "Capital do Ceará"),
                new City("Salvador","Capital da Bahia"),
                new City("São Luiz","Capital do Maranhão"),
                new City("Teresina","Capital do Piauí"),
                new City("Rio de Janeiro", "Capital do Rio de Janeiro"),
                new City("São Paulo","Capital de São Paulo"),
                new City("Vitória","Capital do Espirito Santo"),
                new City("Belo Horizonte","Capital do Minas Gerais"),
                new City("Florianópolis","Capital de Santa Catarina"),
                new City("Curitiba","Capital do Paraná"),
                new City("Porto Alegre","Capital do Rio Grande do Sul"),
                new City("Macapá","Capital do Amapá"),
                new City("Porto Velho","Capital de Rondônia"),
                new City("Palmas","Capital do Tocantins"),
                new City("Boa Vista","Capital do Roraima"),
                new City("Belém","Capital do Pará"),
                new City("Rio Branco","Capital do Acre"),
                new City("Manaus","Capital do Amazonas"),
                new City("Goiania","Capital do Goias"),
                new City("Cuiabá","Capital do Mato Grosso"),
                new City("Campo Grande","Capital do Mato Grosso do Sul")
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new CityArrayListAdapter(this,
                        R.layout.city_listitem, cities
                )
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Toast.makeText(parent.getContext(), "Cidade selecionada: " +
                        cities[position].getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}