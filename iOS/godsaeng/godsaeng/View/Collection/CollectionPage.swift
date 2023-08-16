//
//  CollectionPage.swift
//  godsaeng
//
//  Created by Suji Lee on 2023/08/15.
//

import SwiftUI

struct CollectionPage: View {
    
    @StateObject var godsaengVM: GodSaengViewModel = GodSaengViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
                Text("# 모집중")
                    .font(.system(size: 26, weight: .bold))
                    .padding(.leading)
                    .padding(.bottom)

            //같생 전체 목록
            ScrollView {
                LazyVStack(alignment: .leading, spacing: 5) {
                    ForEach(godsaengVM.godsaengList, id: \.self) { godsaeng in
                        NavigationLink(destination: {
                            GodsaengDetailPage(godsaengVM: godsaengVM, godsaeng: godsaeng)
                        }, label: {
                            CollectionGSCell(godsaeng: godsaeng, mode: .extended)
                        })
                        
                    }
                }
            }
            Button(action: {
                //같생 생성 트리거 on
            }, label: {
                RoundedRectangle(cornerRadius: 20)
                    .foregroundColor(.mainGreen)
                    .frame(width: screenWidth * 0.85, height: 45)
                    .overlay (
                        Text("새로운 같생 만들기")
                            .font(.system(size: 15, weight: .semibold))
                            .foregroundColor(.white)
                    )
            })
            .padding()
        }
        .padding()
        .onAppear {
            if let token = try? TokenManager.shared.getToken() {
                godsaengVM.fetchGodsaengList(accessToken: token)
            }
        }
    }
}

struct CollectionPage_Previews: PreviewProvider {
    static var previews: some View {
        CollectionPage()
    }
}
