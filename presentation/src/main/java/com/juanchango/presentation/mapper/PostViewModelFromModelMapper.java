package com.juanchango.presentation.mapper;

import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps objects from Domain Layer to Presentation Layer
 * {@link PostModel} to {@link com.juanchango.presentation.viewmodel.PostViewModel}.
 */
@Singleton
public class PostViewModelFromModelMapper {

    @Inject
    PostViewModelFromModelMapper() {
    }

    private static PostViewModelFromModelMapper instance;
    public static PostViewModelFromModelMapper getInstance(){
        if(instance == null){
            instance = new PostViewModelFromModelMapper();
        }
        return instance;
    }

    /**
     * Transform bellow objects.
     *
     * @param postModel {@link PostModel}
     * @return {@link PostViewModel}
     */
    public PostViewModel transform (PostModel postModel){
        PostViewModel postViewModel = null;

        if(postModel != null){
            postViewModel = new PostViewModel(postModel.getPostId());
            postViewModel.setTitle(postModel.getTitle());
            postViewModel.setBody(postModel.getBody());
            postViewModel.setUserId(postModel.getUserId());
        }
        return postViewModel;
    }

    /**
     * Transform listo of bellow objects.
     *
     * @param postModels List of {@link PostModel}
     * @return List of {@link PostViewModel}
     */
    public List<PostViewModel> transform(List<PostModel> postModels){
        final List<PostViewModel> postViewModels = new ArrayList<>();
        if(postModels != null){
            for (PostModel postModel:postModels){
                final PostViewModel postViewModel = this.transform(postModel);
                if(postViewModel != null){
                    postViewModels.add(postViewModel);
                }
            }
        }
        return postViewModels;
    }

}
