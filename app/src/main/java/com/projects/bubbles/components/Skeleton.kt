package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.ui.theme.Amber200
import com.projects.bubbles.ui.theme.Blue200
import com.projects.bubbles.ui.theme.Fuchsia200
import com.projects.bubbles.ui.theme.Green200
import com.projects.bubbles.ui.theme.Lime200
import com.projects.bubbles.ui.theme.Orange200
import com.projects.bubbles.ui.theme.Red300
import com.projects.bubbles.ui.theme.Teal200
import com.projects.bubbles.ui.theme.Violet300
import com.projects.bubbles.utils.AnimationSlider
import com.projects.bubbles.utils.ShimmerEffect


val colors = listOf(
    Amber200,
    Blue200,
    Fuchsia200,
    Green200,
    Lime200,
    Orange200,
    Teal200,
    Violet300,
    Red300
)

@Composable
fun PostBoxSkeleton() {
  AnimationSlider {
      Surface(
          Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(brush = ShimmerEffect()),
          color = Color(0xFFe4e4e4)
      ) {
          Column(modifier = Modifier.padding(12.dp)) {
              Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
              ) {
                  Spacer(
                      modifier = Modifier
                          .size(32.dp)
                          .clip(RoundedCornerShape(100.dp))
                          .background(brush = ShimmerEffect())
                  )

                  Spacer(Modifier.width(8.dp))

                  Row(
                      modifier = Modifier.width(200.dp),
                      horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                      Spacer(
                          modifier = Modifier
                              .width(80.dp)
                              .height(12.dp)
                              .clip(RoundedCornerShape(6.dp))
                              .background(brush = ShimmerEffect()),
                      )

                      Spacer(
                          modifier = Modifier
                              .width(60.dp)
                              .height(12.dp)
                              .clip(RoundedCornerShape(6.dp))
                              .background(brush = ShimmerEffect()),
                      )

                      repeat(2) {
                          Spacer(
                              modifier = Modifier
                                  .width(20.dp)
                                  .height(12.dp)
                                  .clip(RoundedCornerShape(6.dp))
                                  .background(brush = ShimmerEffect()),
                          )
                      }


                      Spacer(modifier = Modifier.height(4.dp))

                  }
              }

              Spacer(modifier = Modifier.height(8.dp))

              repeat(3) {
                  Spacer(
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(14.dp)
                          .clip(RoundedCornerShape(6.dp))
                          .background(brush = ShimmerEffect())
                  )
                  Spacer(modifier = Modifier.height(4.dp))
              }
          }
      }
  }
}

@Composable
fun BubbleCardSkeleton() {
    AnimationSlider {
        Box(
            modifier = Modifier
                .size(235.dp)
                .background(brush = ShimmerEffect(), shape = RoundedCornerShape(16.dp))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .height(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(100.dp)
                            .height(25.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(colors.random())
                            .background(brush = ShimmerEffect())
                    )

                    Row {
                        Spacer(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(brush = ShimmerEffect())
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Spacer(
                            modifier = Modifier
                                .width(30.dp)
                                .height(12.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(brush = ShimmerEffect())
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(20.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(brush = ShimmerEffect())
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(12.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(brush = ShimmerEffect())
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(12.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(brush = ShimmerEffect())
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = ShimmerEffect(),
                            shape = RoundedCornerShape(16.dp)
                        )
                )
            }
        }
    }
}

@Composable
fun EventCardSkeleton() {
   AnimationSlider {
       Card(
           modifier = Modifier
               .fillMaxWidth()
               .height(280.dp),
           shape = RoundedCornerShape(16.dp),
       ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
           ) {
               Spacer(
                   modifier = Modifier
                       .weight(0.35f)
                       .fillMaxHeight()
                       .clip(RoundedCornerShape(16.dp))
                       .background(brush = ShimmerEffect())
               )

               Spacer(modifier = Modifier.width(16.dp))

               Column(
                   modifier = Modifier
                       .weight(0.6f)
                       .fillMaxHeight()
                       .padding(0.dp, 20.dp, 16.dp, 20.dp)
               ) {
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       Spacer(
                           modifier = Modifier
                               .width(100.dp)
                               .height(25.dp)
                               .clip(RoundedCornerShape(6.dp))
                               .background(colors.random())
                               .background(brush = ShimmerEffect())
                       )
                   }

                   Spacer(Modifier.height(8.dp))

                   Column(
                       Modifier.fillMaxHeight(),
                       verticalArrangement = Arrangement.SpaceBetween
                   ) {
                       Column {
                           Spacer(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(20.dp)
                                   .clip(RoundedCornerShape(6.dp))
                                   .background(brush = ShimmerEffect())
                           )

                           Spacer(Modifier.height(8.dp))

                           Spacer(
                               modifier = Modifier
                                   .fillMaxWidth(0.8f)
                                   .height(16.dp)
                                   .clip(RoundedCornerShape(6.dp))
                                   .background(brush = ShimmerEffect())
                           )
                       }

                       Column(){
                           Row {
                               Spacer(
                                   modifier = Modifier
                                       .size(16.dp)
                                       .clip(RoundedCornerShape(6.dp))
                                       .background(brush = ShimmerEffect())
                               )

                               Spacer(Modifier.width(6.dp))

                               Spacer(
                                   modifier = Modifier
                                       .fillMaxWidth(0.9f)
                                       .height(16.dp)
                                       .clip(RoundedCornerShape(6.dp))
                                       .background(brush = ShimmerEffect())
                               )
                           }

                           Spacer(Modifier.height(12.dp))

                           Row {
                               Row{
                                   Spacer(
                                       modifier = Modifier
                                           .size(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )

                                   Spacer(Modifier.width(6.dp))

                                   Spacer(
                                       modifier = Modifier
                                           .width(32.dp)
                                           .height(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )
                               }

                               Spacer(Modifier.width(18.dp))

                               Row{
                                   Spacer(
                                       modifier = Modifier
                                           .size(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )

                                   Spacer(Modifier.width(6.dp))

                                   Spacer(
                                       modifier = Modifier
                                           .width(32.dp)
                                           .height(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )
                               }

                               Spacer(Modifier.width(18.dp))

                               Row{
                                   Spacer(
                                       modifier = Modifier
                                           .size(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )

                                   Spacer(Modifier.width(6.dp))

                                   Spacer(
                                       modifier = Modifier
                                           .width(32.dp)
                                           .height(16.dp)
                                           .clip(RoundedCornerShape(6.dp))
                                           .background(brush = ShimmerEffect())
                                   )
                               }
                           }
                       }


                       Spacer(
                           modifier = Modifier
                               .fillMaxWidth()
                               .height(30.dp)
                               .clip(RoundedCornerShape(100.dp))
                               .background(brush = ShimmerEffect())
                       )
                   }
               }
           }
       }
   }
}

@Preview
@Composable
fun SkeletonPreview() {
    EventCardSkeleton()
}