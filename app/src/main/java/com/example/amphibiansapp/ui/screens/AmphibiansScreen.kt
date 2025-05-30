package com.example.amphibiansapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme
import com.example.amphibiansapp.viewmodel.AmphibiansUiState


@Composable
fun AmphibiansScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_values)),
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansListScreen(
            amphibians = amphibiansUiState.amphibians,
            contentPadding = contentPadding,
            modifier = modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                )
        )
        is AmphibiansUiState.Error -> ErrorScreen(retryAction = retryAction)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier.size(dimensionResource(R.dimen.image_loading_size))
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.amphibian_greeting),
            contentDescription = stringResource(R.string.amphibian_greeting),
            modifier = Modifier.size(dimensionResource(R.dimen.image_error_size))
        )

        Text(
            text = stringResource(R.string.loading_failed),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
        )

        Button(
            onClick = retryAction,
            modifier = Modifier.width(dimensionResource(R.dimen.button_size))
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.titleLarge
                )
        }
    }
}

@Composable
fun AmphibiansListScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_values))
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(items = amphibians) { amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_extra_small))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraSmall,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ){
                Text(
                    text = stringResource(R.string.amphibians_title, amphibian.name, amphibian.type),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .fillMaxWidth()
                )
            }
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ErrorPreview() {
    AmphibiansAppTheme {
        ErrorScreen(
            retryAction = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
