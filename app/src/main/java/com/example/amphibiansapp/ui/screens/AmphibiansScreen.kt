package com.example.amphibiansapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.viewmodel.AmphibiansUiState


@Composable
fun AmphibiansScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansGridScreen(
            amphibians = amphibiansUiState.amphibians,
            contentPadding = contentPadding, modifier = modifier)
        is AmphibiansUiState.Error -> ErrorScreen(retryAction = retryAction)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(painter = painterResource(R.drawable.ic_connection_error), contentDescription = "")

        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(
            dimensionResource(R.dimen.padding_large)))

        Button(onClick = retryAction) { Text(text = stringResource(R.string.retry)) }
    }
}

@Composable
fun AmphibiansGridScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(bottom = dimensionResource(R.dimen.padding_large)),
        contentPadding = contentPadding
    ) {
        items(amphibians) {
            amphibian -> AmphibianCard(amphibian = amphibian, modifier = modifier)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.amphibians_title, amphibian.name, amphibian.type),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .align(Alignment.Start)

        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(amphibian.imgSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = amphibian.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = amphibian.description,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
        )
    }
}