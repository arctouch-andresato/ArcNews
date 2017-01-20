package com.arctouch.arcnews.ui;

import com.arctouch.arcnews.R;
import com.arctouch.arcnews.domain.rss.Item;
import com.arctouch.arcnews.dummy.DummyContent.DummyItem;
import com.arctouch.arcnews.infra.ObservableAdapter;
import com.arctouch.arcnews.presenter.PostListPresenter;
import com.arctouch.arcnews.view.PostsListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.val;
import rx.Observable;
import rx.functions.Action0;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PostListFragment extends Fragment implements PostsListView {
    private static final String TAG = PostListFragment.class.getSimpleName();

    @Inject
    PostListPresenter presenter;

    @BindView(R.id.list)
    RecyclerView postsView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private Unbinder unbinder;
    private ObservableAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating view...");
        val mainView = inflater.inflate(R.layout.fragment_post_list, container, false);
        this.unbinder = ButterKnife.bind(this, mainView);

        this.listAdapter = new ObservableAdapter();
        this.postsView.setAdapter(listAdapter);
        this.postsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.postsView.setHasFixedSize(true);

        this.presenter.setupView(this);

        return mainView;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    @Override
    public void showData(Observable<List<Item>> posts) {
        this.listAdapter.setObservable(posts);
    }

    @Override
    public void showRefreshProgress() {
        Log.d(TAG, "Setting refreshing to true...");
        this.refreshLayout.post(() ->
            this.refreshLayout.setRefreshing(true)
        );
    }

    @Override
    public void hideRefreshProgress() {
        Log.d(TAG, "Setting refreshing to false...");
        this.refreshLayout.post(() ->
            this.refreshLayout.setRefreshing(false)
        );
    }

    @Override
    public void setOnRefreshListener(Action0 listener) {
        this.refreshLayout.setOnRefreshListener(listener::call);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
