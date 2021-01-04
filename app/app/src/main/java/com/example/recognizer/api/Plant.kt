package com.example.recognizer.api

data class Plant(
    val `data`: Data,
    val meta: Meta
)

data class Data(
    val author: String,
    val bibliography: String,
    val common_name: String,
    val common_names: CommonNames,
    val distribution: Distribution,
    val distributions: Distributions,
    val duration: List<String>,
    val edible: Boolean,
    val edible_part: List<String>,
    val family: String,
    val family_common_name: String,
    val flower: Flower,
    val foliage: Foliage,
    val fruit_or_seed: FruitOrSeed,
    val genus: String,
    val genus_id: Int,
    val growth: Growth,
    val id: Int,
    val image_url: String,
    val images: Images,
    val links: LinksXX,
    val observations: String,
    val rank: String,
    val scientific_name: String,
    val slug: String,
    val sources: List<Source>,
    val specifications: Specifications,
    val status: String,
    val synonyms: List<Synonym>,
    val vegetable: Boolean,
    val year: Int
)

data class Meta(
    val images_count: Int,
    val last_modified: String,
    val sources_count: Int,
    val synonyms_count: Int
)

data class CommonNames(
    val af: List<String>,
    val ak: List<String>,
    val am: List<String>,
    val ar: List<String>,
    val az: List<String>,
    val be: List<String>,
    val bg: List<String>,
    val bi: List<String>,
    val bn: List<String>,
    val bo: List<String>,
    val br: List<String>,
    val ca: List<String>,
    val cs: List<String>,
    val cy: List<String>,
    val da: List<String>,
    val de: List<String>,
    val deu: List<String>,
    val dv: List<String>,
    val el: List<String>,
    val en: List<String>,
    val eng: List<String>,
    val eo: List<String>,
    val es: List<String>,
    val et: List<String>,
    val eu: List<String>,
    val fa: List<String>,
    val fi: List<String>,
    val fj: List<String>,
    val fr: List<String>,
    val fra: List<String>,
    val fy: List<String>,
    val ga: List<String>,
    val gl: List<String>,
    val gn: List<String>,
    val gu: List<String>,
    val gv: List<String>,
    val ha: List<String>,
    val he: List<String>,
    val hi: List<String>,
    val hr: List<String>,
    val ht: List<String>,
    val hu: List<String>,
    val hy: List<String>,
    val ia: List<String>,
    val id: List<String>,
    val ig: List<String>,
    val io: List<String>,
    val `is`: List<String>,
    val `it`: List<String>,
    val ja: List<String>,
    val jv: List<String>,
    val ka: List<String>,
    val kk: List<String>,
    val km: List<String>,
    val kn: List<String>,
    val ku: List<String>,
    val lb: List<String>,
    val li: List<String>,
    val ln: List<String>,
    val lo: List<String>,
    val lt: List<String>,
    val lv: List<String>,
    val mi: List<String>,
    val mk: List<String>,
    val ml: List<String>,
    val mn: List<String>,
    val mr: List<String>,
    val ms: List<String>,
    val msa: List<String>,
    val mt: List<String>,
    val my: List<String>,
    val nb: List<String>,
    val ne: List<String>,
    val nl: List<String>,
    val nn: List<String>,
    val nv: List<String>,
    val or: List<String>,
    val pa: List<String>,
    val pl: List<String>,
    val por: List<String>,
    val ps: List<String>,
    val pt: List<String>,
    val qu: List<String>,
    val ro: List<String>,
    val ru: List<String>,
    val sa: List<String>,
    val sd: List<String>,
    val si: List<String>,
    val sk: List<String>,
    val sl: List<String>,
    val so: List<String>,
    val spa: List<String>,
    val sq: List<String>,
    val sr: List<String>,
    val su: List<String>,
    val sv: List<String>,
    val sw: List<String>,
    val swa: List<String>,
    val swe: List<String>,
    val ta: List<String>,
    val te: List<String>,
    val th: List<String>,
    val tl: List<String>,
    val to: List<String>,
    val tr: List<String>,
    val tt: List<String>,
    val ty: List<String>,
    val uk: List<String>,
    val ur: List<String>,
    val vi: List<String>,
    val wo: List<String>,
    val yi: List<String>,
    val yo: List<String>
)

data class Distribution(
    val introduced: List<String>,
    val native: List<String>
)

data class Distributions(
    val introduced: List<Introduced>,
    val native: List<Native>
)

data class Flower(
    val color: Any,
    val conspicuous: Boolean
)

data class Foliage(
    val color: List<String>,
    val leaf_retention: Boolean,
    val texture: String
)

data class FruitOrSeed(
    val color: List<String>,
    val conspicuous: Boolean,
    val seed_persistence: Boolean,
    val shape: Any
)

data class Growth(
    val atmospheric_humidity: Any,
    val bloom_months: Any,
    val days_to_harvest: Any,
    val description: String,
    val fruit_months: Any,
    val growth_months: Any,
    val light: Int,
    val maximum_precipitation: MaximumPrecipitation,
    val maximum_temperature: MaximumTemperature,
    val minimum_precipitation: MinimumPrecipitation,
    val minimum_root_depth: MinimumRootDepth,
    val minimum_temperature: MinimumTemperature,
    val ph_maximum: Double,
    val ph_minimum: Double,
    val row_spacing: RowSpacing,
    val soil_humidity: Any,
    val soil_nutriments: Any,
    val soil_salinity: Any,
    val soil_texture: Int,
    val sowing: String,
    val spread: Spread
)

data class Images(
    val bark: List<Bark>,
    val flower: List<FlowerX>,
    val fruit: List<Fruit>,
    val habit: List<Habit>,
    val leaf: List<Leaf>,
    val other: List<Other>
)

data class LinksXX(
    val genus: String,
    val plant: String,
    val self: String
)

data class Source(
    val citation: String,
    val id: String,
    val last_update: String,
    val name: String,
    val url: String
)

data class Specifications(
    val average_height: AverageHeight,
    val growth_form: String,
    val growth_habit: String,
    val growth_rate: String,
    val ligneous_type: String,
    val maximum_height: MaximumHeight,
    val nitrogen_fixation: String,
    val shape_and_orientation: String,
    val toxicity: String
)

data class Synonym(
    val author: String,
    val id: Int,
    val name: String,
    val sources: List<SourceX>
)

data class Introduced(
    val id: Int,
    val links: Links,
    val name: String,
    val slug: String,
    val species_count: Int,
    val tdwg_code: String,
    val tdwg_level: Int
)

data class Native(
    val id: Int,
    val links: LinksX,
    val name: String,
    val slug: String,
    val species_count: Int,
    val tdwg_code: String,
    val tdwg_level: Int
)

data class Links(
    val plants: String,
    val self: String,
    val species: String
)

data class LinksX(
    val plants: String,
    val self: String,
    val species: String
)

data class MaximumPrecipitation(
    val mm: Int
)

data class MaximumTemperature(
    val deg_c: Int,
    val deg_f: Int
)

data class MinimumPrecipitation(
    val mm: Int
)

data class MinimumRootDepth(
    val cm: Int
)

data class MinimumTemperature(
    val deg_c: Int,
    val deg_f: Int
)

data class RowSpacing(
    val cm: Any
)

data class Spread(
    val cm: Any
)

data class Bark(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class FlowerX(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class Fruit(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class Habit(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class Leaf(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class Other(
    val copyright: String,
    val id: Int,
    val image_url: String
)

data class AverageHeight(
    val cm: Int
)

data class MaximumHeight(
    val cm: Int
)

data class SourceX(
    val citation: String,
    val id: String,
    val last_update: String,
    val name: String,
    val url: String
)