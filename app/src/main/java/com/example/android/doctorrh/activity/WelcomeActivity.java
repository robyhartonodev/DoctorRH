package com.example.android.doctorrh.activity;

import com.example.android.doctorrh.R;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeConfiguration;

public class WelcomeActivity extends com.stephentuso.welcome.WelcomeActivity {
    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.secondaryLightColor)
                .page(new TitlePage(R.drawable.ic_local_hospital,
                        "Title")
                )
                .page(new BasicPage(R.drawable.ic_local_hospital,
                        "Header",
                        "More text.")
                        .background(R.color.secondaryColor)
                )
                .page(new BasicPage(R.drawable.ic_local_hospital,
                        "Lorem ipsum",
                        "dolor sit amet.")
                        .background(R.color.secondaryDarkColor)
                )
                .swipeToDismiss(true)
                .build();
    }
}
