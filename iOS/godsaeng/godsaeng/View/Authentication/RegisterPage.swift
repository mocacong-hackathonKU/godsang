//
//  RegisterPage.swift
//  godsaeng
//
//  Created by Suji Lee on 2023/08/07.
//

import SwiftUI
import PhotosUI

struct RegisterPage: View {
    
    @State var profileImageData: Data?
    @State var selectedPhotos: [PhotosPickerItem] = []
    @State var nickname: String = ""
    
    var body: some View {
        NavigationView {
            VStack {
                VStack {
                Text("회원가입")
                    .font(.system(size: 27, weight: .bold))
                    .padding(.top, -20)
                    .padding(.bottom, 45)
                    VStack(alignment: .leading) {
                        Text("프로필 설정")
                            .font(.system(size: 22, weight: .bold))
                            .foregroundColor(.accent4)
                            .padding(.bottom, 7)
                        Text("나를 잘 나타내는 사진과 닉네임을 정해주세요")
                            .font(.system(size: 16, weight: .semibold))
                            .foregroundColor(.darkGray)
                        Text("프로필은 같생을 함께할 사람들에게 보여집니다")
                            .font(.system(size: 14))
                            .foregroundColor(.darkGray)
                    }
                    .padding(.leading, -70)
                }
                .padding(.bottom, 35)
                VStack {
                    //이미지
                    Image("DefaultProfile")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 180, height: 180)
                        .clipped()
                        .overlay(
                            PhotosPicker(selection: $selectedPhotos, maxSelectionCount: 1, matching: .images) {
                                Circle()
                                    .foregroundColor(.clear)
                                    .frame(width: 180, height: 180)
                            }
                                .onChange(of: selectedPhotos) { newItem in
                                    guard let item = selectedPhotos.first else {
                                        return
                                    }
                                    item.loadTransferable(type: Data.self) { result in
                                        switch result {
                                        case .success(let data):
                                            if let data = data {
                                                self.profileImageData = data
                                            } else {
                                                print("data is nil")
                                            }
                                        case .failure(let failure):
                                            fatalError("\(failure)")
                                        }
                                    }
                                }
                        )
                    
                }
                .padding(.bottom, 40)
                VStack(alignment: .leading) {
                    TextField("닉네잉 (변경불가)", text: $nickname)
                    Rectangle()
                        .foregroundColor(.lightGray)
                        .frame(width: 340, height: 3)
                    VStack(alignment: .leading) {
                        Text("닉네임은 한글과 영어 2~6자로 입력해주세요")
                        Text("중복된 닉네임입니다")
                    }
                    .font(.system(size: 13))
                    .foregroundColor(.alertRed)
                }
                .padding(.leading, 30)
            }
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: {
                        
                    }, label: {
                        Text("취소")
                            .foregroundColor(.darkGray)
                    })
                }
                
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        
                    }, label: {
                        Text("완료")
                            .foregroundColor(.mainGreen)
                    })
                }
            }
        }
    }
}

struct RegisterPage_Previews: PreviewProvider {
    static var previews: some View {
        RegisterPage()
    }
}