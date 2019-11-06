package interview.upendra.com.basicstructure.dynamiclist;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArtistViewModel.class)) {
            return (T) new ArtistViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
