package trpl.nim234311041.trofimonitor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import trpl.nim234311041.trofimonitor.ui.theme.TrofiMonitorTheme

@Composable
fun AddClubScreen(onClubAdded: (Club) -> Unit = {}) {
    var clubName by remember { mutableStateOf("") }
    var premierLeague by remember { mutableStateOf("") }
    var faCup by remember { mutableStateOf("") }
    var eflCup by remember { mutableStateOf("") }
    var championsLeague by remember { mutableStateOf("") }
    var europaLeague by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nama: Hanan Labib",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "NIM: 234311041",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = clubName,
            onValueChange = { clubName = it },
            label = { Text("Club Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = premierLeague,
            onValueChange = { premierLeague = it },
            label = { Text("Premier League Wins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )

        OutlinedTextField(
            value = faCup,
            onValueChange = { faCup = it },
            label = { Text("FA Cup Wins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )

        OutlinedTextField(
            value = eflCup,
            onValueChange = { eflCup = it },
            label = { Text("EFL Cup Wins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )

        OutlinedTextField(
            value = championsLeague,
            onValueChange = { championsLeague = it },
            label = { Text("Champions League Wins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )

        OutlinedTextField(
            value = europaLeague,
            onValueChange = { europaLeague = it },
            label = { Text("Europa League Wins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newClub = Club(
                    name = clubName,
                    premierLeague = premierLeague.toIntOrNull() ?: 0,
                    faCup = faCup.toIntOrNull() ?: 0,
                    eflCup = eflCup.toIntOrNull() ?: 0,
                    championsLeague = championsLeague.toIntOrNull() ?: 0,
                    europaLeague = europaLeague.toIntOrNull() ?: 0
                )
                onClubAdded(newClub)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Club")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddClubScreenPreview() {
    TrofiMonitorTheme {
        AddClubScreen()
    }
}