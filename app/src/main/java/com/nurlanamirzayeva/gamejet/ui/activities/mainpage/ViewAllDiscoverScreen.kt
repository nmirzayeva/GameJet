package com.nurlanamirzayeva.gamejet.ui.activities.mainpage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.nurlanamirzayeva.gamejet.ui.theme.black
import com.nurlanamirzayeva.gamejet.ui.theme.dark_grey
import com.nurlanamirzayeva.gamejet.ui.theme.grey
import com.nurlanamirzayeva.gamejet.utils.BACKGROUND_URL
import com.nurlanamirzayeva.gamejet.utils.IMAGE_URL
import com.nurlanamirzayeva.gamejet.viewmodel.MainPageViewModel

@Composable
fun ViewAllDiscoverScreen(mainPageViewModel: MainPageViewModel) {

    val discoverPageList = mainPageViewModel.discoverListPager.collectAsLazyPagingItems()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = grey)
                .height(100.dp)
        ){


            Text(text ="List",color=Color.White, fontSize = 30.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.align(
                Alignment.CenterStart).padding(start = 50.dp))
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = dark_grey), verticalArrangement = Arrangement.spacedBy(8.dp)


        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = black)
                    .height(230.dp)
            ) {
                AsyncImage(model = BACKGROUND_URL+mainPageViewModel.firstDiscoverImage.value, contentDescription = null, modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.FillBounds )

            }

            Text(
                text = "Discover great movies and TV shows for your Watchlist",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )



            if (discoverPageList.itemSnapshotList.items.isNotEmpty()) {

                LazyVerticalGrid(columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),


                    content = {

                        items(discoverPageList.itemCount) { index ->
                            DiscoverItem(imageUrl = IMAGE_URL + discoverPageList[index]!!.posterPath)
                        }

                        with(discoverPageList)

                        {
                            when {
                                loadState.refresh is LoadState.Loading -> {

                                    item {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .size(50.dp),
                                            color = Color.White
                                        )
                                    }
                                }

                                loadState.refresh is LoadState.Error -> {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                                }


                                loadState.append is LoadState.Loading -> {

                                    item {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .size(50.dp),
                                            color = Color.White
                                        )
                                    }


                                }

                                loadState.append is LoadState.Error -> {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                                }

                            }


                        }


                    })

            }

        }

    }

}


@Composable
fun DiscoverItem(imageUrl: String) {


    Box(
        modifier = Modifier
            .background(color = black)
            .height(130.dp)
            .width(100.dp)
    ) {

        AsyncImage(
            model = imageUrl, contentDescription = null, modifier = Modifier

                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

    }


}