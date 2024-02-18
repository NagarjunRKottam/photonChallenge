import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.model.NewYorkSchoolRepository
import com.interview.photon.viewmodel.NewyorkSchoolViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewyorkSchoolViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val repository: NewYorkSchoolRepository = mockk()
    private lateinit var viewModel: NewyorkSchoolViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NewyorkSchoolViewModel()
        viewModel.setRepository(repository)
    }

    @Test
    fun `fetchNySchool should update LiveData with fetched schools`() {
        // Given
        val mockSchools = listOf(
            NewYorkSchool("dbn", "School 1", "123", "Overview 1"),
            NewYorkSchool("dbn", "School 2", "456", "Overview 2")
        )
        coEvery { repository.getNewYorkSchool() } returns mockSchools

        // When
        viewModel.fetchNySchool()

        // Then
        assertEquals(mockSchools, viewModel.nySchool.value)
    }
}