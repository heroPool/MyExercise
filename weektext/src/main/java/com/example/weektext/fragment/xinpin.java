package com.example.weektext.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weektext.I;
import com.example.weektext.R;
import com.example.weektext.bean.UserBean;
import com.example.weektext.utils.OkImageLoader;
import com.example.weektext.utils.OkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class xinpin extends Fragment {

    private static final int ACTION_DOWNLOAD = 1;
    private static final int ACTION_PULL_DOWN = 2;
    private static final int ACTION_PULL_UP = 3;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipefresh;
    LinearLayoutManager linearlayoutManager;
    ArrayList<UserBean> userBeanListm;
    TextView textHint;
    RecyclerViewAdapter recyclerAdapter;

    int pageId = 1;

    public xinpin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_xinpin, container, false);

        initView(inflate);

        downloadContactList(ACTION_DOWNLOAD, 1);
        setListener(inflate);
        return inflate;
    }

    private void setListener(View inflate) {

        //下拉
        swipefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkImageLoader.release();
                swipefresh.setRefreshing(true);
                pageId = 1;
                downloadContactList(ACTION_PULL_DOWN, pageId);
                textHint.setVisibility(View.VISIBLE);

                textHint.setText("玩命加载中...");
            }
        });
        //上拉
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = linearlayoutManager.findLastVisibleItemPosition();
                if (position == recyclerAdapter.getItemCount() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE && recyclerAdapter.isMore) {
                    pageId++;
                    downloadContactList(ACTION_PULL_UP, pageId);
                }
            }
        });
    }

    private void initView(View inflate) {
        textHint = (TextView) inflate.findViewById(R.id.textHint);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        linearlayoutManager = new LinearLayoutManager(getActivity());
        swipefresh = (SwipeRefreshLayout) inflate.findViewById(R.id.swiperefreshlayout);
        userBeanListm = new ArrayList<>();
        recyclerAdapter = new RecyclerViewAdapter(getActivity(), userBeanListm);


        recyclerView.setLayoutManager(linearlayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void downloadContactList(final int action, int pageid) {
        final OkUtils<UserBean[]> okUtils = new OkUtils<>(getActivity());
        okUtils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageid + "")
                .addParam(I.PAGE_SIZE, 10 + "")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        recyclerAdapter.setMore(result != null && result.length > 0);
                        if (!recyclerAdapter.isMore()) {
                            if (action == ACTION_PULL_UP) {
                                recyclerAdapter.setFootertext("没有更多数据了");

                            }
                            return;
                        }
                        ArrayList<UserBean> userBeens = okUtils.array2List(result);
                        switch (action) {
                            case ACTION_DOWNLOAD:
                                recyclerAdapter.initContact(userBeens);
                                recyclerAdapter.setFootertext("加载更多");
                                break;
                            case ACTION_PULL_DOWN:
                                recyclerAdapter.initContact(userBeens);
                                swipefresh.setRefreshing(false);
                                recyclerAdapter.setFootertext("加载更多");
                                textHint.setVisibility(View.GONE);

                                break;
                            case ACTION_PULL_UP:
                                recyclerAdapter.addAllContact(userBeens);

                                break;
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }


    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        static final int TYPE_FOOTER = 1;
        static final int TYPE_CONTACT = 2;

        Context context;
        ArrayList<UserBean> userBeenList;

        public RecyclerViewAdapter(Context context, ArrayList<UserBean> userBeenList) {
            this.context = context;
            this.userBeenList = userBeenList;
        }

        boolean isMore;
        String footertext;

        public void setFootertext(String footertext) {
            this.footertext = footertext;
            notifyDataSetChanged();

        }

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER) {
                View inflate = View.inflate(context, R.layout.item_footer, null);
                return new FooterHolder(inflate);

            }
            View inflate = View.inflate(context, R.layout.item_contact, null);

            return new ContactHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterHolder footerHolder = (FooterHolder) holder;
                footerHolder.textFooter.setVisibility(View.VISIBLE);
                footerHolder.textFooter.setText(footertext);
                return;
            }
            ContactHolder contactHolder = (ContactHolder) holder;
            UserBean userBean = userBeenList.get(position);
            contactHolder.textNick.setText(userBean.getNick());
            contactHolder.textUsername.setText(userBean.getUserName());

            OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL + userBean.getUserName())
                    .defaultPicture(R.drawable.default_face)
                    .imageView(contactHolder.imageViewAvatar)

                    .showImage(context);
        }

        @Override
        public int getItemCount() {
            return userBeenList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {

                return TYPE_FOOTER;
            }
            return TYPE_CONTACT;
        }

        public void initContact(ArrayList<UserBean> userBeens) {
            this.userBeenList.clear();
            this.userBeenList.addAll(userBeens);
            notifyDataSetChanged();

        }

        public void addAllContact(ArrayList<UserBean> userBeens) {
            this.userBeenList.addAll(userBeens);

            notifyDataSetChanged();

        }
    }


    class FooterHolder extends RecyclerView.ViewHolder {

        TextView textFooter;

        public FooterHolder(View itemView) {
            super(itemView);
            textFooter = (TextView) itemView.findViewById(R.id.footer_text);

        }
    }

    class ContactHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAvatar;
        TextView textUsername, textNick;

        public ContactHolder(View itemView) {
            super(itemView);
            imageViewAvatar = (ImageView) itemView.findViewById(R.id.imageAvatar);
            textUsername = (TextView) itemView.findViewById(R.id.textUsername);
            textNick = (TextView) itemView.findViewById(R.id.textNick);

        }
    }

}
