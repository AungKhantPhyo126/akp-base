package com.dev.akp_base.localization

import com.dev.akp_base.localization.model.LanguageUiModel
import com.dev.akp_base.localization.model.LocalizationModel
import com.dev.datasource.LocalizationDataSource
import com.dev.datasource.dataStore.MyDataStoreDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.util.Locale
import javax.inject.Inject

class LocalizationRepoImpl @Inject constructor(
    private val myDataStoreDataSource: MyDataStoreDataSource,
    private val localizationDataSource: LocalizationDataSource
) : LocalizationRepository {

    override val localizationFlow: Flow<LocalizationModel>
        get() = flow {
            localizationDataSource
                .getLocalization()
                .tap { emit(it) }
                .tapLeft { emit(myDataStoreDataSource.getLocalization) }
        }.combine(myDataStoreDataSource.localeFlow){response,locale->
            response.toLocalizationModel(locale)
        }
    override suspend fun changeLocale(locale: Locale) {
        myDataStoreDataSource.changeLocale(locale)
    }

    override fun getLocalization(): LocalizationModel? {
        TODO("Not yet implemented")
    }
}