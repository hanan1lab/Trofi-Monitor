package trpl.nim234311041.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import trpl.nim234311041.trofimonitor.ui.theme.TrofiMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrofiMonitorTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    var clubs by remember { mutableStateOf(createClubList()) }

    NavHost(navController = navController, startDestination = "clubList") {
        composable("clubList") {
            ClubListScreen(navController, clubs)
        }
        composable("addClub") {
            AddClubScreen { newClub ->
                clubs = clubs + newClub
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun ClubListScreen(navController: NavController, clubs: List<Club>) {
    val studentName = "Hanan Labib R"
    val studentID = "234311041"

    val filterClubs = clubs.filter { it.totalTrophies > 30 }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Nama: $studentName", style = MaterialTheme.typography.titleLarge)
        Text(text = "NIM: $studentID", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(clubs) { club ->
                ClubRow(club)
            }
        }

        Button(
            onClick = { navController.navigate("addclub") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Tambah Data Klub")
        }

        Text(text = "Klub yang memiliki lebih dari 30 trofi:", style = MaterialTheme.typography.bodyLarge)
        filterClubs.forEach { club ->
            Text(text = "${club.name}: ${club.totalTrophies} trofi")
        }
    }
}

@Composable
fun ClubRow(club: Club) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        ClubLogo(club)
        Column {
            Text(text = club.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Total Trofi: ${club.totalTrophies}", style = MaterialTheme.typography.bodySmall)
            if (club.totalTrophies == 0) {
                Text(text = "${club.name} belum pernah memenangkan trofi.", style = MaterialTheme.typography.bodySmall)
            } else if (club.internationalTrophies == 0) {
                Text(text = "${club.name} belum pernah memenangkan trofi internasional.", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun ClubLogo(club: Club) {
    val logoResId = when (club.name) {
        "Manchester United" -> R.drawable.mu
        "Liverpool" -> R.drawable.liverpool
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
}

fun createClubList(): List<Club> {
    return listOf(
        Club("Liverpool", premierLeague = 19, faCup = 8, eflCup = 10, championsLeague = 6, europaLeague = 3),
        Club("Manchester United", premierLeague = 20, faCup = 12, eflCup = 6, championsLeague = 3, europaLeague = 1),
        Club("Chelsea", premierLeague = 6, faCup = 8, eflCup = 5, championsLeague = 2, europaLeague = 2),
        Club("Manchester City", premierLeague = 9, faCup = 7, eflCup = 8, championsLeague = 1, europaLeague = 0),
        Club("Arsenal", premierLeague = 13, faCup = 14, eflCup = 2, championsLeague = 0, europaLeague = 0),
        Club("Tottenham Hotspur", premierLeague = 2, faCup = 8, eflCup = 4, championsLeague = 0, europaLeague = 0),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrofiMonitorTheme {
        val navController = rememberNavController()
        val clubs = createClubList()
        ClubListScreen(navController, clubs)
    }
}