import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.model.NewYorkSchoolRepository
import com.interview.photon.viewmodel.NewYorkSchoolViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewyorkSchoolViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Create a TestCoroutineDispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    // Mock repository using mockk
    private val repository: NewYorkSchoolRepository = mockk()

    // Subject under test
    private lateinit var viewModel: NewYorkSchoolViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NewYorkSchoolViewModel()
        viewModel.setRepository(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchNySchool success`() {
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

    @Test
    fun `fetchNySchool failure`() {
        // Given
        val errorMessage = "Failed to fetch schools"
        coEvery { repository.getNewYorkSchool() } throws RuntimeException(errorMessage)

        // When
        viewModel.fetchNySchool()

        // Then
        assertNull(viewModel.nySchool.value)
    }
}
