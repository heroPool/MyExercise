package com.example.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.list.bean.UserBean;
import com.example.list.utils.OkImageLoader;
import com.example.list.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;
    RecyclerView mrvContact;
    ArrayList<UserBean> mContactList;
    int mPageId;
    LinearLayoutManager mLayoutManager;
    ContactAdapter mContactAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mtvRefreshHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId = 1;
        downloadContactList(mPageId, ACTION_DOWNLOAD);
        setListener();
    }

    private void setListener() {
        setPullDownListener();
        setPullUPListener();
    }

    private void setPullDownListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkImageLoader.release();
                mSwipeRefreshLayout.setRefreshing(true);
                mtvRefreshHint.setVisibility(View.VISIBLE);
                mPageId = 1;
                downloadContactList(mPageId, ACTION_PULL_DOWN);
            }
        });
    }

    private void setPullUPListener() {
        mrvContact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = mLayoutManager.findLastVisibleItemPosition();
                if (position == mContactAdapter.getItemCount() - 1 && RecyclerView.SCROLL_STATE_IDLE == newState && mContactAdapter.isMore()) {
                    mPageId++;
                    downloadContactList(mPageId, ACTION_PULL_UP);
                }
            }
        });
    }

    private void downloadContactList(int mPageId, final int action) {
        final OkUtils<UserBean[]> okUtils = new OkUtils<>(this);
        okUtils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, mPageId + "")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        mContactAdapter.setMore(result != null && result.length > 0);
                        if (!mContactAdapter.isMore) {
                            if (action == ACTION_PULL_UP) {
                                mContactAdapter.setFooterText("没有更多数据了");
                            }
                            return;
                        }
                        ArrayList<UserBean> contactList = okUtils.array2List(result);
                        switch (action) {
                            case ACTION_DOWNLOAD:
                                mContactAdapter.initContactList(contactList);

                                mContactAdapter.setFooterText("加载更多");
                                break;
                            case ACTION_PULL_DOWN:
                                mContactAdapter.initContactList(contactList);
                                mContactAdapter.setFooterText("加载更多");
                                mSwipeRefreshLayout.setRefreshing(false);
                                mtvRefreshHint.setVisibility(View.GONE);

                                break;
                            case ACTION_PULL_UP:
                                mContactAdapter.addContactList(contactList);
                                mContactAdapter.setFooterText("加载更多");

                                break;
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        mtvRefreshHint = (TextView) findViewById(R.id.tvRefreshHint);
        mrvContact = (RecyclerView) findViewById(R.id.rvContact);

        mLayoutManager = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(mLayoutManager);

        mContactList = new ArrayList<>();
        mContactAdapter = new ContactAdapter(this, mContactList);
        mrvContact.setAdapter(mContactAdapter);
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;

        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);

        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvUserName, tvNick;

        public ItemHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNick = (TextView) itemView.findViewById(R.id.tvNike);

        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_FOOTER = 0;
        static final int TYPE_ITEM = 1;


        Context context;
        ArrayList<UserBean> contactList;
        String footerText;

        public ContactAdapter(Context context, ArrayList<UserBean> contactList) {
            this.context = context;
            this.contactList = contactList;
        }

        public void setFooterText(String footerText) {
            this.footerText = footerText;
            notifyDataSetChanged();
        }

        boolean isMore;

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_FOOTER:
                    return new FooterHolder(View.inflate(context, R.layout.item_footer, null));
                case TYPE_ITEM:
                    return new ItemHolder(View.inflate(context, R.layout.item_contact, null));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterHolder footerHolder = (FooterHolder) holder;
                footerHolder.tvFooter.setVisibility(View.VISIBLE);
                footerHolder.tvFooter.setText(footerText);
                return;

            }
            UserBean user = contactList.get(position);
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.tvUserName.setText(user.getUserName());
            itemHolder.tvNick.setText(user.getNick());

            OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL + user.getUserName())
                    .defaultPicture(R.drawable.default_face)
                    .imageView(((ItemHolder) holder).ivAvatar)
                    .showImage(context);
        }

        @Override
        public int getItemCount() {
            return contactList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }


        public void initContactList(ArrayList<UserBean> contactList) {
            this.contactList.clear();
            this.contactList.addAll(contactList);
            Log.i("main", contactList.toString());
            notifyDataSetChanged();
        }

        public void addContactList(ArrayList<UserBean> contactList) {
            this.contactList.addAll(contactList);
            notifyDataSetChanged();

        }

    }
}


