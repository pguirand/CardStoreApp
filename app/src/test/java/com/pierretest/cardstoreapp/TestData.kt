package com.pierretest.cardstoreapp

import com.pierretest.cardstoreapp.data.models.CardImageModel
import com.pierretest.cardstoreapp.data.models.CardPriceModel
import com.pierretest.cardstoreapp.data.models.CardSetModel
import com.pierretest.cardstoreapp.data.models.DataModel

object TestData {

    val card1 = DataModel(
        id = 12345678,
        name = "Example Card 1",
        type = "Monster Card",
        frameType = "monster",
        desc = "This is an example card description 1.",
        race = "Aqua",
        cardSets = listOf(
            CardSetModel(
                setName = "Starter Deck: XYZ",
                setCode = "XYZ-EN001",
                setRarity = "Common",
                setRarityCode = "(C)",
                setPrice = "0.99"
            )
        ),
        cardImages = listOf(
            CardImageModel(
                id = 12345678,
                imageUrl = "https://example.com/images/cards/12345678.jpg",
                imageUrlSmall = "https://example.com/images/cards_small/12345678.jpg",
                imageUrlCropped = "https://example.com/images/cards_cropped/12345678.jpg"
            )
        ),
        cardPrices = listOf(
            CardPriceModel(
                cardmarketPrice = "1.50",
                tcgplayerPrice = "1.25",
                ebayPrice = "3.00",
                amazonPrice = "2.50",
                coolstuffincPrice = "1.99"
            )
        )
    )

    val card2 = DataModel(
        id = 98765432,
        name = "Example Card 2",
        type = "Spell Card",
        frameType = "spell",
        desc = "This is another example card description.",
        race = "Magic",
        cardSets = listOf(
            CardSetModel(
                setName = "Starter Deck: ABC",
                setCode = "ABC-EN002",
                setRarity = "Rare",
                setRarityCode = "(R)",
                setPrice = "1.99"
            )
        ),
        cardImages = listOf(
            CardImageModel(
                id = 98765432,
                imageUrl = "https://example.com/images/cards/98765432.jpg",
                imageUrlSmall = "https://example.com/images/cards_small/98765432.jpg",
                imageUrlCropped = "https://example.com/images/cards_cropped/98765432.jpg"
            )
        ),
        cardPrices = listOf(
            CardPriceModel(
                cardmarketPrice = "2.50",
                tcgplayerPrice = "2.25",
                ebayPrice = "4.00",
                amazonPrice = "3.50",
                coolstuffincPrice = "2.99"
            )
        )
    )

    val card3 = DataModel(
        id = 33333333,
        name = "Example Card 3",
        type = "Trap Card",
        frameType = "trap",
        desc = "This is yet another example card description.",
        race = "Trap",
        cardSets = listOf(
            CardSetModel(
                setName = "Starter Deck: XYZ",
                setCode = "XYZ-EN003",
                setRarity = "Ultra Rare",
                setRarityCode = "(UR)",
                setPrice = "5.99"
            )
        ),
        cardImages = listOf(
            CardImageModel(
                id = 33333333,
                imageUrl = "https://example.com/images/cards/33333333.jpg",
                imageUrlSmall = "https://example.com/images/cards_small/33333333.jpg",
                imageUrlCropped = "https://example.com/images/cards_cropped/33333333.jpg"
            )
        ),
        cardPrices = listOf(
            CardPriceModel(
                cardmarketPrice = "3.50",
                tcgplayerPrice = "4.25",
                ebayPrice = "6.00",
                amazonPrice = "5.50",
                coolstuffincPrice = "4.99"
            )
        )
    )

    val card4 = DataModel(
        id = 44444444,
        name = "Example Card 4",
        type = "Monster Card",
        frameType = "monster",
        desc = "Description for card 4.",
        race = "Beast",
        cardSets = listOf(
            CardSetModel(
                setName = "Starter Deck: ABC",
                setCode = "ABC-EN004",
                setRarity = "Common",
                setRarityCode = "(C)",
                setPrice = "1.49"
            )
        ),
        cardImages = listOf(
            CardImageModel(
                id = 44444444,
                imageUrl = "https://example.com/images/cards/44444444.jpg",
                imageUrlSmall = "https://example.com/images/cards_small/44444444.jpg",
                imageUrlCropped = "https://example.com/images/cards_cropped/44444444.jpg"
            )
        ),
        cardPrices = listOf(
            CardPriceModel(
                cardmarketPrice = "2.00",
                tcgplayerPrice = "1.75",
                ebayPrice = "3.25",
                amazonPrice = "2.99",
                coolstuffincPrice = "1.95"
            )
        )
    )

    val card5 = DataModel(
        id = 55555555,
        name = "Example Card 5",
        type = "Spell Card",
        frameType = "spell",
        desc = "Description for card 5.",
        race = "Magic",
        cardSets = listOf(
            CardSetModel(
                setName = "Booster Pack: Elements",
                setCode = "ELE-EN005",
                setRarity = "Ultra Rare",
                setRarityCode = "(UR)",
                setPrice = "10.99"
            )
        ),
        cardImages = listOf(
            CardImageModel(
                id = 55555555,
                imageUrl = "https://example.com/images/cards/55555555.jpg",
                imageUrlSmall = "https://example.com/images/cards_small/55555555.jpg",
                imageUrlCropped = "https://example.com/images/cards_cropped/55555555.jpg"
            )
        ),
        cardPrices = listOf(
            CardPriceModel(
                cardmarketPrice = "9.00",
                tcgplayerPrice = "9.25",
                ebayPrice = "12.00",
                amazonPrice = "11.50",
                coolstuffincPrice = "9.99"
            )
        )
    )
}