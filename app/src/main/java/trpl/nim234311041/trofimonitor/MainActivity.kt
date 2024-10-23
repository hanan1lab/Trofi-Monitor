package trpl.nim234311041.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    private val studentName = "Nama : Hanan Labib R"
    private val studentID = "NIM: 234311041"

    private val clubs = mutableStateListOf(
        Club("Liverpool", 19, 8, 10, 6, 3),
        Club("Manchester United", 20, 12, 6, 3, 1),
        Club("Chelsea", 6, 8, 5, 2, 2),
        Club("Manchester City", 9, 7, 8, 1, 0),
        Club("Arsenal", 13, 14, 2, 0, 0),
        Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrofiMonitorApp()
        }
    }

    @Composable
    fun TrofiMonitorApp() {
        var showAddClubScreen by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = studentName, style = MaterialTheme.typography.titleLarge)
            Text(text = studentID, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            if (showAddClubScreen) {
                AddClubScreen(onClubAdded = { newClub ->
                    clubs.add(newClub)
                    showAddClubScreen = false
                })
            } else {
                DisplayClubs(clubs)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showAddClubScreen = true }) {
                    Text("Tambah Klub Baru")
                }

                DisplayClubsWithMoreThan30Trophies(clubs)
            }
        }
    }

    @Composable
    fun DisplayClubs(clubs: List<Club>) {
        val sortedClubs = clubs.sortedByDescending { it.totalTrophies }

        for (club in sortedClubs) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val logoResId = when (club.name) {
                    "Liverpool" -> R.drawable.liverpool
                    "Manchester United" -> R.drawable.mu
                    "Chelsea" -> R.drawable.chelsea
                    "Manchester City" -> R.drawable.mancity
                    "Arsenal" -> R.drawable.arsenal__fc
                    "Tottenham Hotspur" -> R.drawable.tottenham
                    else -> R.drawable.logo
                }

                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "${club.name} Logo",
                    modifier = Modifier.size(64.dp).padding(end = 8.dp)
                )
                Column {
                    Text(text = club.name)
                    Text(text = "Total Trofi: ${club.totalTrophies}")
                    if (club.championsLeague == 0 && club.europaLeague == 0) {
                        Text(
                            text = "${club.name} Belum pernah memenangkan trofi internasional",
                           
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Composable
    fun DisplayClubsWithMoreThan30Trophies(clubs: List<Club>) {
        val filteredClubs = clubs.filter { it.totalTrophies > 30 }

        if (filteredClubs.isEmpty()) {
            Text(text = "Tidak ada klub dengan lebih dari 30 trofi.")
        } else {
            Column {
                Text(
                    text = "Klub dengan lebih dari 30 trofi:",
                    style = MaterialTheme.typography.titleMedium
                )
                for (club in filteredClubs) {
                    Column {
                        Text(text = "${club.name}: ${club.totalTrophies} trofi")
                        if (club.championsLeague == 0 && club.europaLeague == 0) {
                            Text(
                                text = "Belum pernah memenangkan trofi internasional",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}